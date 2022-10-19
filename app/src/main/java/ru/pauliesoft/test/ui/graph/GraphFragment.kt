package ru.pauliesoft.test.ui.graph

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.pauliesoft.test.databinding.FragmentGraphBinding
import ru.pauliesoft.test.ui.base.BaseFragment

class GraphFragment : BaseFragment() {

    override lateinit var binding: FragmentGraphBinding
    override val viewModel: GraphViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentGraphBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews() {

    }

    override fun setupObservers() {

    }
}