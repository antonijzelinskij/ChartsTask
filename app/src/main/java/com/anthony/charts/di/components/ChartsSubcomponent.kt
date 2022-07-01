package com.anthony.charts.di.components

import com.anthony.charts.di.modules.ChartsModule
import com.anthony.charts.di.scopes.ChartsScope
import com.anthony.charts.ui.charts.ChartsPresenter
import com.anthony.charts.ui.chartsettings.ChartSettingsPresenter
import dagger.Subcomponent

@ChartsScope
@Subcomponent(modules = [ChartsModule::class])
interface ChartsSubcomponent {
    fun inject(chartSettingsPresenter: ChartSettingsPresenter)
}