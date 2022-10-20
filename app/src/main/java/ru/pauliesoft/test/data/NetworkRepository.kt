package ru.pauliesoft.test.data

import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : NetworkRepositoryContract {

    override suspend fun getPoints(count: Int): PointsResponse {
        return retrofitAPI.getPoints(count)
    }
}