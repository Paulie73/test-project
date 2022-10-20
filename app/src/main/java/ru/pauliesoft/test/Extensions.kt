package ru.pauliesoft.test

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.google.android.material.snackbar.Snackbar
import ru.pauliesoft.test.ui.base.BaseActivity

fun Fragment.showLoader(isShow: Boolean) {
    (activity as? BaseActivity)?.showLoader(isShow)
}

fun showSnackBar(rootView: View, message: String) {
    Snackbar.make(
        rootView, message, Snackbar.LENGTH_LONG
    ).show()
}

fun Fragment.backPressed() {
    (activity as? BaseActivity)?.onBackPressed()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.smoothShow(animationDuration: Long = 300, animationEndBlock: () -> Unit = {}) {
    if (isVisible) return

    alpha = 0f
    isVisible = true
    animate().alpha(1f).setDuration(animationDuration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                clearAnimation()
                animationEndBlock()
            }
        })
}

fun View.smoothHide(animationDuration: Long = 300, animationEndBlock: () -> Unit = {}) {
    if (!isVisible) return

    alpha = 1f
    animate().alpha(0f).setDuration(animationDuration)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                isVisible = false
                clearAnimation()
                animationEndBlock()
            }
        })
}

fun NavController.navigateWithAnimation(directions: NavDirections) {
    navigate(
        directions,
        NavOptions.Builder().setEnterAnim(R.anim.screen_slide_in_right)
            .setExitAnim(R.anim.screen_slide_out_left).setPopEnterAnim(R.anim.screen_slide_in_left)
            .setPopExitAnim(R.anim.screen_slide_out_right).build()
    )
}

fun NavController.navigateWithAnimation(
    resId: Int,
    args: Bundle? = null,
    @IdRes popUpToDestinationId: Int? = null,
    popUpToInclusive: Boolean = false
) {
    val navOptionsBuilder = NavOptions.Builder().setEnterAnim(R.anim.screen_slide_in_right)
        .setExitAnim(R.anim.screen_slide_out_left).setPopEnterAnim(R.anim.screen_slide_in_left)
        .setPopExitAnim(R.anim.screen_slide_out_right)

    popUpToDestinationId?.let {
        navOptionsBuilder.setPopUpTo(it, popUpToInclusive)
    }

    navigate(resId, args, navOptionsBuilder.build())
}