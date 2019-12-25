package com.weatherapp.di

import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.SearchResponse
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MockRemoteModule(private val response: SearchResponse? = null){
    @Provides
    fun weatherApi():WeatherApi{
        return MockWeatherApi(response)
    }
}