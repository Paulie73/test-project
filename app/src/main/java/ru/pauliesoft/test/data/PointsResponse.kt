package ru.pauliesoft.test.data

import com.squareup.moshi.Json

data class PointsResponse(
    @field:Json(name = "points") val points: List<Point>
) {

    data class Point(
        @field:Json(name = "x") val x: Double,
        @field:Json(name = "y") val y: Double
    )
}