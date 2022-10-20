package ru.pauliesoft.test.ui.mappers

import ru.pauliesoft.test.data.PointsResponse

data class Point(
    val x: Double, val y: Double
)

fun PointsResponse.toPointList(): List<Point> {
    return points.map { Point(it.x, it.y) }
}