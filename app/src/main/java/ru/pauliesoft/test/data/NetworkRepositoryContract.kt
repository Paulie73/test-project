package ru.pauliesoft.test.data

interface NetworkRepositoryContract {

    suspend fun getPoints(count: Int): PointsResponse

}