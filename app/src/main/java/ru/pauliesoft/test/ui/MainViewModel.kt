package ru.pauliesoft.test.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.pauliesoft.test.R
import ru.pauliesoft.test.domain.GetPointsInteractor
import ru.pauliesoft.test.ui.base.BaseViewModel
import ru.pauliesoft.test.ui.livedata_wrapper.Event
import ru.pauliesoft.test.ui.mappers.Point
import ru.pauliesoft.test.ui.mappers.toPointList
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPointsInteractor: GetPointsInteractor
) : BaseViewModel() {

    private val _navigateToGraphScreen = MutableLiveData<Event<Unit>>()
    val navigateToGraphScreen: LiveData<Event<Unit>> = _navigateToGraphScreen

    val isGraphSmooth = MutableLiveData(false)

    var numberTabSelected = 0

    var count = 0

    val points = MutableLiveData<List<Point>>()

    fun onCountChanged(count: Int) {
        this.count = count
    }

    fun onGoButtonClicked() {
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                when {
                    count == 0 -> showSnackBar(R.string.enter_number)
                    count < 0 -> showSnackBar(R.string.enter_positive_number)
                    else -> {
                        showLoader(true)
                        val pointList = getPointsInteractor.getPoints(count)
                            .toPointList()
                            .sortedBy { point -> point.x }
                        points.postValue(emptyList())
                        _navigateToGraphScreen.postValue(Event(Unit))
                        delay(500)
                        points.postValue(pointList)
                        showLoader(false)
                    }
                }
            }
        }
    }
}