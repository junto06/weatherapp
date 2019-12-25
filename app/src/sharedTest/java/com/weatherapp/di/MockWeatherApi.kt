package com.weatherapp.di

import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.SearchResponse
import io.reactivex.Observable
import java.lang.Exception

class MockWeatherApi(private val response:SearchResponse?):WeatherApi{
    override fun searchCity(key: String, format: String, q: String): Observable<SearchResponse> {
        return if(response != null){
            Observable.just(response)
        }else{
            Observable.error(Exception("Mock exception!!"))
        }
    }

}