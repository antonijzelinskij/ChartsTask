package com.anthony.charts.models

import android.os.Parcelable
import com.anthony.charts.domain.points.entity.PointGroup
import com.anthony.charts.utils.ViewModelMapper
import kotlinx.parcelize.Parcelize

@Parcelize
class PointGroupViewModel(val points: List<PointViewModel>) : ViewModel(), Parcelable {
    fun getSortedPointsByX(): List<PointViewModel> {
        return points.sortedBy { it.x }
    }

    class Mapper : ViewModelMapper<PointGroupViewModel, PointGroup> {
        private val pointMapper = PointViewModel.Mapper()

        override fun toEntity(from: PointGroupViewModel): PointGroup {
            return PointGroup(
                pointMapper.toEntityList(from.points)
            )
        }

        override fun toViewModel(from: PointGroup): PointGroupViewModel {
            return PointGroupViewModel(
                pointMapper.toViewModelList(from.points)
            )
        }
    }
}