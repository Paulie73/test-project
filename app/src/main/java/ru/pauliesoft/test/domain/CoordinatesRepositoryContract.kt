package ru.pauliesoft.test.domain

import ru.pauliesoft.test.data.PointsResponse

interface CoordinatesRepositoryContract {

    suspend fun getPoints(count: Int): PointsResponse

}