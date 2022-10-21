package ru.pauliesoft.test.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pauliesoft.test.data.PointsResponse
import javax.inject.Inject

class GetPointsInteractor @Inject constructor(
    private val networkRepository: CoordinatesRepositoryContract
) {

    suspend fun getPoints(
        count: Int
    ): PointsResponse {
        return withContext(Dispatchers.IO) {
            networkRepository.getPoints(count)
        }
    }
}