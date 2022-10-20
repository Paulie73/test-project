package ru.pauliesoft.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.pauliesoft.test.R
import ru.pauliesoft.test.ui.base.BaseViewModel
import ru.pauliesoft.test.ui.livedata_wrapper.Event
import ru.pauliesoft.test.ui.mappers.Point
import kotlin.random.Random

class MainViewModel : BaseViewModel() {

    private val _navigateToGraphScreen = MutableLiveData<Event<Unit>>()
    val navigateToGraphScreen: LiveData<Event<Unit>> = _navigateToGraphScreen

    var numberTabSelected = 0

    var count = 0

    val points = MutableLiveData<List<Point>>()

    fun onCountChanged(count: Int) {
        this.count = count
    }

    fun onGoButtonClicked() {
        viewModelScope.launch {
            when {
                count == 0 -> showSnackBar(R.string.enter_number)
                count < 0 -> showSnackBar(R.string.enter_positive_number)
                else -> {
                    showLoader(true)
                    delay(1000)
                    points.postValue(
                        listOf(
                            Point(Random.nextDouble(), Random.nextDouble()),
                            Point(Random.nextDouble(), Random.nextDouble()),
                            Point(Random.nextDouble(), Random.nextDouble()),
                            Point(Random.nextDouble(), Random.nextDouble()),
                            Point(Random.nextDouble(), Random.nextDouble())
                        ).sortedBy { point -> point.x }
                    )
                    _navigateToGraphScreen.postValue(Event(Unit))
                    showLoader(false)
                }
            }
        }
    }
}