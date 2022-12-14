package ru.pauliesoft.test.ui.result.tabs

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.pauliesoft.test.R
import ru.pauliesoft.test.databinding.FragmentTableBinding
import ru.pauliesoft.test.ui.MainViewModel
import ru.pauliesoft.test.ui.base.BaseFragment
import ru.pauliesoft.test.ui.mappers.Point

@AndroidEntryPoint
class TableFragment : BaseFragment() {

    override lateinit var binding: FragmentTableBinding
    override val viewModel: MainViewModel by activityViewModels()

    companion object {
        private const val DEFAULT_PADDING = 20
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTableBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews() {

    }

    override fun setupObservers() {
        viewModel.points.observe(viewLifecycleOwner) {
            drawTable(it)
        }
    }

    private fun drawTable(points: List<Point>) {
        binding.tableLayout.removeAllViews()
        binding.tableLayout.addView(
            createTableRow(
                getString(R.string.x_axis),
                getString(R.string.y_axis)
            )
        )
        for (point in points) {
            binding.tableLayout.addView(createTableRow(point.x.toString(), point.y.toString()))
        }
    }

    private fun createTableRow(firstValue: String, secondValue: String): TableRow {
        val tableRow = TableRow(requireContext()).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT
            )
            gravity = Gravity.CENTER_HORIZONTAL
        }
        tableRow.addView(TextView(context).apply {
            text = firstValue
            setTextColor(Color.BLACK)
            setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING)
            gravity = Gravity.CENTER_HORIZONTAL
        })
        tableRow.addView(TextView(context).apply {
            text = secondValue
            setTextColor(Color.BLACK)
            setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING)
            gravity = Gravity.CENTER_HORIZONTAL
        })
        return tableRow
    }
}