package com.anthony.charts.ui.main

import com.anthony.charts.ui.base.BasePresenter

class MainPresenter : BasePresenter<MainViewState>() {
    override fun onFirstViewAttach() {
        viewState.openChartSettings()
    }
}