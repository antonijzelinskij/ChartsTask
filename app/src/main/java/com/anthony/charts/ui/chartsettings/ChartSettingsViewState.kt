package com.anthony.charts.ui.chartsettings

import com.anthony.charts.models.PointGroupViewModel
import com.anthony.charts.ui.base.BaseViewState
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ChartSettingsViewState : BaseViewState {
    @StateStrategyType(SkipStrategy::class)
    fun onPointsLoad(pointGroup: PointGroupViewModel)

    @StateStrategyType(SkipStrategy::class)
    fun onStartLoading()

    @StateStrategyType(SkipStrategy::class)
    fun onStopLoading()
}