package com.weatherapp.domain.usecase

import com.weatherapp.data.model.City
import com.weatherapp.data.model.CurrentWeather
import io.reactivex.Observable

interface WeatherRepo{
    fun loadCityWeather(city: City): Observable<CurrentWeather>
}