package ru.pauliesoft.test.domain

import ru.pauliesoft.test.data.NetworkRepositoryContract
import ru.pauliesoft.test.data.PointsResponse
import javax.inject.Inject

class GetPointsInteractor @Inject constructor(
    private val networkRepository: NetworkRepositoryContract
) {

    suspend fun getPoints(
        count: Int
    ): PointsResponse {
        return networkRepository.getPoints(count)
    }
}