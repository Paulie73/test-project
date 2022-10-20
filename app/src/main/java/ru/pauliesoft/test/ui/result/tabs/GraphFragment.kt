package ru.pauliesoft.test.ui.result.tabs

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import ru.pauliesoft.test.R
import ru.pauliesoft.test.databinding.FragmentGraphBinding
import ru.pauliesoft.test.showSnackBar
import ru.pauliesoft.test.ui.MainViewModel
import ru.pauliesoft.test.ui.base.BaseFragment
import ru.pauliesoft.test.ui.mappers.Point
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


@AndroidEntryPoint
class GraphFragment : BaseFragment() {

    override lateinit var binding: FragmentGraphBinding
    override val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews() {
        binding.radioButtonStraight.setOnClickListener { viewModel.isGraphSmooth.postValue(false) }
        binding.radioButtonSmooth.setOnClickListener { viewModel.isGraphSmooth.postValue(true) }
        binding.saveToFileButton.setOnClickListener { saveMediaToStorage(binding.lineChart.chartBitmap) }
    }

    override fun setupObservers() {
        viewModel.points.observe(viewLifecycleOwner) {
            drawGraph(it)
        }

        viewModel.isGraphSmooth.observe(viewLifecycleOwner) {
            with(binding) {
                radioButtonSmooth.isChecked = it
                radioButtonStraight.isChecked = !it
            }

            viewModel.points.value?.let { drawGraph(it) }
        }
    }

    private fun drawGraph(points: List<Point>) {
        val entryList = points.map { point -> Entry(point.x.toFloat(), point.y.toFloat()) }

        with(binding) {
            lineChart.data = LineData(LineDataSet(entryList, "").apply {
                if (viewModel.isGraphSmooth.value == true) {
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                }
            })
            lineChart.invalidate()
        }
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        //Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        //Output stream
        var fos: OutputStream? = null

        //For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //getting the contentResolver
            context?.contentResolver?.also { resolver ->

                //Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    //putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                //Inserting the contentValues to contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                //Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            //These for devices running on android < Q
            //So I don't think an explanation is needed here
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            //Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            showSnackBar(binding.root, getString(R.string.file_saved))
        }
    }
}