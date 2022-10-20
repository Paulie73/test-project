package ru.pauliesoft.test.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): PointsResponse

}