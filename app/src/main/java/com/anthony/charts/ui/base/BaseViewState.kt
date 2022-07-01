package com.anthony.charts.ui.base

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface BaseViewState : MvpView {
    @StateStrategyType(SkipStrategy::class)
    fun onError(@StringRes stringRes: Int)
}