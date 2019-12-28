package com.weatherapp.data.repo.remote

import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.DataSource
import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.data.repo.remote.mapper.WeatherMapper
import com.weatherapp.util.network.HttpConfig
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val httpConfig: HttpConfig,
                                           private val weatherApi:WeatherApi,
                                           private val cityMapper: CityMapper,
                                           private val weatherMapper: WeatherMapper):DataSource{
    override fun searchCity(cityName: String): Observable<SearchResult> {
        return weatherApi.searchCity(httpConfig.apiKey(),httpConfig.format(),cityName)
            .map {
                cityMapper.map(it)
            }
    }

    override fun getCurrentWeather(latlong: String): Observable<CurrentWeather> {
        return weatherApi.currentWeather(httpConfig.apiKey(),httpConfig.format(),latlong)
            .map {
                weatherMapper.map(it)
            }
    }
}