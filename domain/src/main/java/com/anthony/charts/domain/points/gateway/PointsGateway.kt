package com.anthony.charts.domain.points.gateway

import com.anthony.charts.domain.points.entity.Point
import com.anthony.charts.domain.points.entity.PointGroup

interface PointsGateway {
    suspend fun getPoints(count: Int): PointGroup
}