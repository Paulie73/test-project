package ru.pauliesoft.test.ui.base

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.viewbinding.ViewBinding
import ru.pauliesoft.test.showSnackBar
import ru.pauliesoft.test.ui.livedata_wrapper.EventObserver

abstract class BaseActivity : AppCompatActivity() {

    abstract val binding: ViewBinding
    protected abstract val viewModel: BaseViewModel

    private var loaderVisibilityAnimator: ValueAnimator? = null
    private var isLoaderVisible = false

    private var loaderLayout: View? = null

    open var onBackPressedBlock: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSnackBarObserver()
        setupLoaderObserver()
    }

    override fun onBackPressed() {
        onBackPressedBlock()
        if (!isLoaderVisible) {
            super.onBackPressed()
        }
    }

    private fun setupSnackBarObserver() {
        viewModel.showSnackBar.observe(this, EventObserver {
            showSnackBar(binding.root, it)
        })

        viewModel.showSnackBarFromRes.observe(this, EventObserver {
            showSnackBar(binding.root, getString(it))
        })
    }

    private fun setupLoaderObserver() {
        viewModel.showLoader.observe(this, EventObserver {
            showLoader(it)
        })
    }

    fun setupLoaderLayout(loaderLayout: View) {
        this.loaderLayout = loaderLayout
    }

    fun showLoader(isShow: Boolean) {
        loaderLayout?.let { loader ->
            loaderVisibilityAnimator?.cancel()
            loaderVisibilityAnimator =
                if (isShow) ValueAnimator.ofFloat(0F, 1F) else ValueAnimator.ofFloat(1F, 0F)

            if (isShow) {
                isLoaderVisible = true
                loaderVisibilityAnimator?.doOnStart {
                    loader.visibility = View.VISIBLE
                    loader.alpha = 0F
                }
            } else {
                loaderVisibilityAnimator?.doOnEnd {
                    loader.visibility = View.GONE
                    isLoaderVisible = false
                }
            }

            loaderVisibilityAnimator?.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                loader.alpha = animatedValue
            }
            loaderVisibilityAnimator?.duration = 200
            loaderVisibilityAnimator?.start()
        }
    }
}