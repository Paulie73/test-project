package ru.pauliesoft.test.ui.input_count

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.pauliesoft.test.ui.base.BaseViewModel
import ru.pauliesoft.test.ui.livedata_wrapper.Event

class InputCountViewModel : BaseViewModel() {

    private val _navigateToGraphScreen = MutableLiveData<Event<Unit>>()
    val navigateToGraphScreen: LiveData<Event<Unit>> = _navigateToGraphScreen

    var count = 0

    fun onCountChanged(count: Int) {
        this.count = count
    }

    fun onGoButtonClicked() {
        viewModelScope.launch {
            showLoader(true)
            delay(3000)
            showLoader(false)
            showSnackBar("asfafae")
            delay(3000)
            _navigateToGraphScreen.postValue(Event(Unit))
        }
    }
}