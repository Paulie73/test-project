package ru.pauliesoft.test.ui.result.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import ru.pauliesoft.test.databinding.FragmentGraphBinding
import ru.pauliesoft.test.ui.MainViewModel
import ru.pauliesoft.test.ui.base.BaseFragment
import ru.pauliesoft.test.ui.mappers.Point

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
}