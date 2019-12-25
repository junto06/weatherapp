package com.weatherapp.data.repo.remote

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.CityDataSource
import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.util.network.HttpConfig
import io.reactivex.Observable
import javax.inject.Inject

class RemoteCityDataSource @Inject constructor(private val httpConfig: HttpConfig,
                                               private val weatherApi:WeatherApi,
                                               private val cityMapper: CityMapper):CityDataSource{
    override fun searchCity(cityName: String): Observable<SearchResult> {

        return weatherApi.searchCity(httpConfig.apiKey(),httpConfig.format(),cityName)
            .map {
                cityMapper.map(it)
            }
    }
}