package ru.pauliesoft.test.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import ru.pauliesoft.test.R
import ru.pauliesoft.test.ui.livedata_wrapper.Event

open class BaseViewModel : ViewModel() {

    private val _showSnackBar = MutableLiveData<Event<String>>()
    val showSnackBar: LiveData<Event<String>> = _showSnackBar

    private val _showSnackBarFromRes = MutableLiveData<Event<Int>>()
    val showSnackBarFromRes: LiveData<Event<Int>> = _showSnackBarFromRes

    private val _showLoader = MutableLiveData<Event<Boolean>>()
    val showLoader: LiveData<Event<Boolean>> = _showLoader

    open var exceptionHandlerBlock = {}

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        showSnackBar(R.string.exception_description)
        showLoader(false)
        exceptionHandlerBlock()
    }

    protected fun showSnackBar(message: String) {
        _showSnackBar.postValue(Event(message))
    }

    protected fun showSnackBar(@StringRes stringRes: Int) {
        _showSnackBarFromRes.postValue(Event(stringRes))
    }

    protected fun showLoader(isShow: Boolean) {
        _showLoader.postValue(Event(isShow))
    }
}