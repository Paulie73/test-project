package ru.pauliesoft.test.ui.result.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import ru.pauliesoft.test.databinding.FragmentGraphBinding
import ru.pauliesoft.test.ui.MainViewModel
import ru.pauliesoft.test.ui.base.BaseFragment

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

    }

    override fun setupObservers() {
        viewModel.points.observe(viewLifecycleOwner) {
            val entryList = it.map { point -> Entry(point.x.toFloat(), point.y.toFloat()) }

            with(binding) {
                lineChart.data = LineData(LineDataSet(entryList, ""))
                lineChart.invalidate()
            }
        }
    }
}