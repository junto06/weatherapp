package com.weatherapp.data.repo.remote.di

import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.util.network.HttpConfig
import com.weatherapp.util.network.OkHttpHelper
import com.weatherapp.util.network.RetrofitHelper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class NetworkModule{

    @Provides
    fun weatherApi(httpConfig: HttpConfig): WeatherApi {
        val httpClient = OkHttpHelper

        val retrofitClient = requireNotNull(RetrofitHelper.createRetrofit(httpConfig,httpClient))

        return retrofitClient.create(WeatherApi::class.java)
    }
}