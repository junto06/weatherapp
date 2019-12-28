package com.weatherapp.mockfactory

import com.weatherapp.data.model.City
import com.weatherapp.data.repo.remote.dto.CityEntry
import com.weatherapp.data.repo.remote.dto.CityInfo

/*
    This factory class generate Mock data for testing purpose
 */
object CityMockFactory{

    fun generateCities():List<CityInfo>{
        return mutableListOf(generateCityInfo("Punggol"),generateCityInfo("Serangoon"))
    }

    fun generateCitiesList():List<City>{
        return mutableListOf(generateCity("Punggol"),generateCity("Serangoon"))
    }

    fun generateCity(name:String):City{
        return City(name,"Singapore","")
    }

    fun generateCityInfo(cityName:String):CityInfo{
        val areaName = listOf(entry(cityName))
        val country = listOf(entry("Singapore"))
        val region = listOf(empty())

        return CityInfo(areaName,country,region)
    }

    private fun entry(content:String): CityEntry{
        val entry = mapOf("value" to content)
        return CityEntry(entry)
    }

    private fun empty(): CityEntry{
        return CityEntry(emptyMap())
    }
}