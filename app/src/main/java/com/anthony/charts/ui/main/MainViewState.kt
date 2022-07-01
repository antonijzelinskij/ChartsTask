package com.anthony.charts.ui.main

import com.anthony.charts.ui.base.BaseViewState
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainViewState : BaseViewState {
    @StateStrategyType(SkipStrategy::class)
    fun openChartSettings()
}