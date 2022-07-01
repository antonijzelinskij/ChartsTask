package com.anthony.charts.di.modules

import com.anthony.charts.BuildConfig
import com.anthony.charts.data.ApiServiceFactory
import com.anthony.charts.data.ChartsApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideChartsApi(): ChartsApi {
        return ApiServiceFactory.makeChartsApi(BuildConfig.BASE_URL)
    }
}