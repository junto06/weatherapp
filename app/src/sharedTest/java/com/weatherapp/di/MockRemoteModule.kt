package com.weatherapp.di

import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse
import com.weatherapp.data.repo.remote.dto.SearchResponse
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MockRemoteModule(private val response: List<Any?> = listOf()){
    constructor(search:SearchResponse?):this(listOf(search,null))
    constructor(weather: CurrentWeatherResponse?):this(listOf(null,weather))

    @Provides
    fun weatherApi():WeatherApi{
        return MockWeatherApi(response)
    }
}