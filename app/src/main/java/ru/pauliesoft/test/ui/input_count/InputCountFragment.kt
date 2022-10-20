package ru.pauliesoft.test.ui.input_count

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.pauliesoft.test.databinding.FragmentInputCountBinding
import ru.pauliesoft.test.hideKeyboard
import ru.pauliesoft.test.navigateWithAnimation
import ru.pauliesoft.test.ui.MainViewModel
import ru.pauliesoft.test.ui.base.BaseFragment
import ru.pauliesoft.test.ui.livedata_wrapper.EventObserver

@AndroidEntryPoint
class InputCountFragment : BaseFragment() {

    override lateinit var binding: FragmentInputCountBinding
    override val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputCountBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViews() {
        binding.countEditText.addTextChangedListener { editable ->
            editable?.toString()?.toIntOrNull()?.let {
                viewModel.onCountChanged(it)
            }
        }

        binding.goButton.setOnClickListener {
            it.hideKeyboard()
            viewModel.onGoButtonClicked()
        }
    }

    override fun setupObservers() {
        viewModel.navigateToGraphScreen.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateWithAnimation(
                InputCountFragmentDirections.actionInputCountFragmentToGraphFragment()
            )
        })
    }
}