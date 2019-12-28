package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse

interface WeatherMapper{
    fun map(searchResponse: CurrentWeatherResponse): CurrentWeather
}