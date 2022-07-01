package com.anthony.charts.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import moxy.MvpPresenter
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<ViewState : BaseViewState> : MvpPresenter<ViewState>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onDestroy() {
        coroutineContext.cancel()
        super.onDestroy()
    }

}