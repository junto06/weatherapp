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


    fun generateCities(list:List<String>,country:String = "Singapore"):List<CityInfo>{
        val cities = mutableListOf<CityInfo>()
        for(city in list){
            cities.add(generateCityInfo(city,country))
        }
        return cities
    }

    fun generateCitiesList(country:String = "Singapore"):List<City>{
        return mutableListOf(generateCity("Punggol",country),generateCity("Serangoon",country))
    }

    fun generateCity(name:String,country:String = "Singapore"):City{
        return City(name,country,"")
    }

    fun generateCityInfo(cityName:String,country:String = "Singapore"):CityInfo{
        val areaName = listOf(entry(cityName))
        val country = listOf(entry(country))
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