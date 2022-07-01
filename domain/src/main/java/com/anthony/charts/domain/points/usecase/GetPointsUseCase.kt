package com.anthony.charts.domain.points.usecase

import com.anthony.charts.domain.points.entity.PointGroup
import com.anthony.charts.domain.points.gateway.PointsGateway

class GetPointsUseCase(private val gateway: PointsGateway) {
    suspend fun execute(count: Int): PointGroup {
        return gateway.getPoints(count)
    }
}