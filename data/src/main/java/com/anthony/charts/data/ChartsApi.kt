package com.anthony.charts.data

import com.anthony.charts.data.response.PointsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChartsApi {
    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): PointsResponse
}