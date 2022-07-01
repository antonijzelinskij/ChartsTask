package com.anthony.charts.models

import android.os.Parcelable
import com.anthony.charts.domain.points.entity.Point
import com.anthony.charts.utils.ViewModelMapper
import kotlinx.parcelize.Parcelize

@Parcelize
data class PointViewModel(val x: Double, val y: Double) : ViewModel(), Parcelable {
    class Mapper : ViewModelMapper<PointViewModel, Point> {
        override fun toEntity(from: PointViewModel): Point {
            return Point(from.x, from.y)
        }

        override fun toViewModel(from: Point): PointViewModel {
            return PointViewModel(from.x, from.y)
        }
    }
}