package ru.pauliesoft.test.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import ru.pauliesoft.test.ui.livedata_wrapper.Event

open class BaseViewModel : ViewModel() {

    private val _showSnackBar = MutableLiveData<Event<String>>()
    val showSnackBar: LiveData<Event<String>> = _showSnackBar

    private val _showLoader = MutableLiveData<Event<Boolean>>()
    val showLoader: LiveData<Event<Boolean>> = _showLoader

    open var exceptionHandlerBlock = {}

    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        showSnackBar(exception.message.toString())
        showLoader(false)
        exceptionHandlerBlock()
    }

    protected fun showSnackBar(message: String) {
        _showSnackBar.postValue(Event(message))
    }

    protected fun showLoader(isShow: Boolean) {
        _showLoader.postValue(Event(isShow))
    }
}