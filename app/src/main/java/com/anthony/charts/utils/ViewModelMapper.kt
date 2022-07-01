package com.anthony.charts.utils

import com.anthony.charts.domain.base.Entity
import com.anthony.charts.models.ViewModel

interface ViewModelMapper<VM : ViewModel, E : Entity> {
    fun toEntity(from: VM): E

    fun toEntityList(from: List<VM>): List<E> {
        return from.map { toEntity(it) }
    }

    fun toViewModel(from: E): VM

    fun toViewModelList(from: List<E>): List<VM> {
        return from.map { toViewModel(it) }
    }
}