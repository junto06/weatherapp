package com.weatherapp.data.repo.remote.api

import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse
import com.weatherapp.data.repo.remote.dto.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{
    @GET("search.ashx")
    fun searchCity(@Query("key") key:String,@Query("format") format:String,
                   @Query("q") q:String): Observable<SearchResponse>

    @GET("weather.ashx")
    fun currentWeather(@Query("key") key:String,@Query("format") format:String,
                   @Query("q") q:String): Observable<CurrentWeatherResponse>


}