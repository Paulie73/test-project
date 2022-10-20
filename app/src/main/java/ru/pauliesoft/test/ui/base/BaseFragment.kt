package ru.pauliesoft.test.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.pauliesoft.test.showLoader
import ru.pauliesoft.test.showSnackBar
import ru.pauliesoft.test.ui.livedata_wrapper.EventObserver

abstract class BaseFragment : Fragment() {

    protected abstract val binding: ViewBinding
    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        setupSnackBarObserver()
        setupLoaderObserver()

        setupViews()
        setupObservers()
        initScreen()

        return binding.root
    }

    private fun setupSnackBarObserver() {
        viewModel.showSnackBar.observe(viewLifecycleOwner, EventObserver {
            (activity as? BaseActivity)?.let { baseActivity ->
                showSnackBar(baseActivity.binding.root, it)
            }
        })

        viewModel.showSnackBarFromRes.observe(viewLifecycleOwner, EventObserver {
            (activity as? BaseActivity)?.let { baseActivity ->
                showSnackBar(baseActivity.binding.root, getString(it))
            }
        })
    }

    private fun setupLoaderObserver() {
        viewModel.showLoader.observe(viewLifecycleOwner, EventObserver {
            showLoader(it)
        })
    }

    protected abstract fun setupViews()

    protected abstract fun setupObservers()

    open fun initScreen() {}
}