package com.anthony.charts.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceFactory {
    fun makeChartsApi(baseUrl: String): ChartsApi {
        val moshi = getMoshi()
        val okHttpClient = getBaseOkHttpClientBuilder()
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        return retrofit.create(ChartsApi::class.java)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }
    
    private fun getBaseOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
    }
}