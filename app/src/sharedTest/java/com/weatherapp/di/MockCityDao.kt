package com.weatherapp.di

import com.weatherapp.data.model.City
import com.weatherapp.data.storage.CityDao
import io.reactivex.Observable
import javax.inject.Inject

class MockCityDao@Inject constructor():CityDao{

    private val map = mutableMapOf<String,City>()

    override fun insertCity(city: City): Long {
        val key = "${city.name}-${city.country}-${city.region}"
        map[key] = city
        return 0
    }

    override fun getRecentCities(): Observable<List<City>> {
        return Observable.just(map.values.toList())
    }

}