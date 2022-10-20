package ru.pauliesoft.test.ui.mappers

data class Point(
    val x: Double,
    val y: Double
)

fun Any.toPointList(): List<Point> {
    return listOf()
}