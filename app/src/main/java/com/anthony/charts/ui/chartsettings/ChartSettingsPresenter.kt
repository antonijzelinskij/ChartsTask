package com.anthony.charts.ui.chartsettings

import com.anthony.charts.R
import com.anthony.charts.di.DIManager
import com.anthony.charts.di.modules.ChartsModule
import com.anthony.charts.domain.points.usecase.GetPointsUseCase
import com.anthony.charts.models.PointGroupViewModel
import com.anthony.charts.ui.base.BasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartSettingsPresenter : BasePresenter<ChartSettingsViewState>() {

    @Inject
    lateinit var getPointsUseCase: GetPointsUseCase

    private val pointGroupMapper = PointGroupViewModel.Mapper()

    init {
        DIManager.applicationComponent
            .createChartsSubcomponent(ChartsModule())
            .inject(this)
    }

    fun loadPoints(count: Int) {
        launch {
            try {
                viewState.onStartLoading()
                val pointGroupEntity = getPointsUseCase.execute(count)
                val pointGroupViewModel = pointGroupMapper.toViewModel(pointGroupEntity)
                viewState.onPointsLoad(pointGroupViewModel)
            } catch (e: Exception) {
                viewState.onError(R.string.chart_settings_fragment_error_message)
            } finally {
                viewState.onStopLoading()
            }
        }
    }
}