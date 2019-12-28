package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse
import javax.inject.Inject

/*
    Weather DTO to domain entity
 */

class WeatherMapperImp @Inject constructor():WeatherMapper{

    override fun map(searchResponse: CurrentWeatherResponse): CurrentWeather {

        val weather = searchResponse.result.currentWeather()

        return CurrentWeather(weather.temp_C,weather.temp_F,weather.humidity,
            weather.observation_time,weather.weatherDesc(),weather.weatherIconUrl())
    }

}