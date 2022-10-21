package ru.pauliesoft.test.data

import retrofit2.http.GET
import retrofit2.http.Query

interface CoordinatesAPI {

    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): PointsResponse

}