package com.weatherapp.data.repo

import com.weatherapp.data.model.SearchResult
import io.reactivex.Observable

interface CityDataSource{
    fun searchCity(cityName:String): Observable<SearchResult>
}