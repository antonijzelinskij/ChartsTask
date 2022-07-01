package com.anthony.charts.data.response

import com.anthony.charts.data.ResponseMapper
import com.anthony.charts.data.common.PointResponse
import com.anthony.charts.domain.points.entity.PointGroup

class PointsResponse(val points: List<PointResponse>) {

    class Mapper : ResponseMapper<PointsResponse, PointGroup> {
        private val pointMapper = PointResponse.Mapper()

        override fun map(from: PointsResponse): PointGroup {
            val points = pointMapper.mapList(from.points)
            return PointGroup(points)
        }
    }
}