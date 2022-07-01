package com.anthony.charts.di.modules

import com.anthony.charts.data.ChartsApi
import com.anthony.charts.data.RemotePointsGateway
import com.anthony.charts.di.scopes.ChartsScope
import com.anthony.charts.domain.points.gateway.PointsGateway
import com.anthony.charts.domain.points.usecase.GetPointsUseCase
import dagger.Module
import dagger.Provides

@Module
class ChartsModule {

    @ChartsScope
    @Provides
    fun getPointsGateway(chartsApi: ChartsApi): PointsGateway {
        return RemotePointsGateway(chartsApi)
    }

    @ChartsScope
    @Provides
    fun getPointsUseCase(pointsGateway: PointsGateway): GetPointsUseCase {
        return GetPointsUseCase(pointsGateway)
    }
}