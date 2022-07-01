package com.anthony.charts.data.common

import com.anthony.charts.data.ResponseMapper
import com.anthony.charts.domain.points.entity.Point

class PointResponse(val x: Double, val y: Double) {
    class Mapper : ResponseMapper<PointResponse, Point> {
        override fun map(from: PointResponse): Point {
            return Point(from.x, from.y)
        }
    }
}