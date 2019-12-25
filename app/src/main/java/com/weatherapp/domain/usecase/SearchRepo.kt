package com.weatherapp.domain.usecase

import com.weatherapp.data.model.SearchResult
import io.reactivex.Observable

interface SearchRepo{
    fun searchCity(cityName:String): Observable<SearchResult>
}