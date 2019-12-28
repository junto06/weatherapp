package com.weatherapp.di

import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse
import com.weatherapp.data.repo.remote.dto.SearchResponse
import io.reactivex.Observable
import java.lang.Exception

class MockWeatherApi(private val response:Any?):WeatherApi{
    override fun currentWeather(
        key: String,
        format: String,
        q: String
    ): Observable<CurrentWeatherResponse> {

        return if(response == null){
            Observable.error(Exception("Mock exception!!"))
        }else{
            val mockResponse = response as CurrentWeatherResponse

            Observable.just(mockResponse)
        }
    }

    override fun searchCity(key: String, format: String, q: String): Observable<SearchResponse> {
        return if(response == null){
            Observable.error(Exception("Mock exception!!"))
        }else{
            val mockResponse = response as SearchResponse?

            Observable.just(mockResponse)
        }
    }
}