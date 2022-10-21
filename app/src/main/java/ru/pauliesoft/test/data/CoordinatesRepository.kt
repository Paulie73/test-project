package ru.pauliesoft.test.data

import ru.pauliesoft.test.domain.CoordinatesRepositoryContract
import javax.inject.Inject

class CoordinatesRepository @Inject constructor(
    private val coordinatesAPI: CoordinatesAPI
) : CoordinatesRepositoryContract {

    override suspend fun getPoints(count: Int): PointsResponse {
        return coordinatesAPI.getPoints(count)
    }
}