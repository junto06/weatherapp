package com.weatherapp.domain.usecase

import com.weatherapp.data.model.City
import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.repo.DataSource
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepoImp@Inject constructor(private val dataSource: DataSource):WeatherRepo{

    override fun loadCityWeather(city: City): Observable<CurrentWeather> {
        val query = "$city.latitude,${city.longitude}"

        return dataSource.getCurrentWeather(query)
    }

}