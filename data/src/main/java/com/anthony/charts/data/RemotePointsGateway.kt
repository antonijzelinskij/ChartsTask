package com.anthony.charts.data

import com.anthony.charts.data.response.PointsResponse
import com.anthony.charts.domain.points.entity.Point
import com.anthony.charts.domain.points.entity.PointGroup
import com.anthony.charts.domain.points.gateway.PointsGateway

class RemotePointsGateway(
    private val chartsApi: ChartsApi
) : PointsGateway {
    override suspend fun getPoints(count: Int): PointGroup {
        val response = chartsApi.getPoints(count)
        val mapper = PointsResponse.Mapper()
        return PointGroup(mapper.map(response).points)
    }
}