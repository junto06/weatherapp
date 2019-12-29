package com.weatherapp.domain.usecase

import com.weatherapp.data.model.City
import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.repo.DataSource
import com.weatherapp.data.storage.CityDao
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepoImp@Inject constructor(private val dataSource: DataSource,
                                        private val cityDao:CityDao):WeatherRepo{

    override fun loadCityWeather(city: City): Observable<CurrentWeather> {

        //update & insert in db
        city.updateAccessTime()
        cityDao.insertCity(city)

        val query = "$city.latitude,${city.longitude}"

        return dataSource.getCurrentWeather(query)
    }
}