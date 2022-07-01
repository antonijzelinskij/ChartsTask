package com.anthony.charts

import android.app.Application
import com.anthony.charts.di.DIManager
import com.anthony.charts.di.components.DaggerApplicationComponent

class ChartsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        DIManager.applicationComponent = DaggerApplicationComponent.builder()
            .setContext(this)
            .build()
    }
}