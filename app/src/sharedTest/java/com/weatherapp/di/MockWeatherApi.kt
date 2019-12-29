package com.weatherapp.di

import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse
import com.weatherapp.data.repo.remote.dto.SearchResponse
import io.reactivex.Observable
import java.lang.Exception

class MockWeatherApi(private val response:List<Any?>):WeatherApi{
    override fun currentWeather(
        key: String,
        format: String,
        q: String
    ): Observable<CurrentWeatherResponse> {

        val weatherResponse = response[1] as CurrentWeatherResponse?

        return if(weatherResponse == null){
            Observable.error(Exception("Mock exception!!"))
        }else{

            Observable.just(weatherResponse)
        }
    }

    override fun searchCity(key: String, format: String, q: String): Observable<SearchResponse> {

        val searchResponse = response[0] as SearchResponse?

        return if(searchResponse == null){
            Observable.error(Exception("Mock exception!!"))
        }else{

            Observable.just(searchResponse)
        }
    }
}