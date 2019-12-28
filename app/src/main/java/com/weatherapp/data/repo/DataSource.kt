package com.weatherapp.data.repo

import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.model.SearchResult
import io.reactivex.Observable

interface DataSource{
    fun searchCity(cityName:String): Observable<SearchResult>

    fun getCurrentWeather(latlong:String): Observable<CurrentWeather>
}