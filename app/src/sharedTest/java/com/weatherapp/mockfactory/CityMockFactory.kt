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

    fun generateCitiesList(list:List<String>,country:String = "Singapore"):List<City>{
        val cities = mutableListOf<City>()
        for(city in list){
            cities.add(generateCity(city,country))
        }
        return cities
    }

    fun generateCity(name:String,country:String = "Singapore",lat:Double = 40.95,
                     long:Double = 60.71):City{
        return City(name,country,"",lat,long)
    }

    fun generateCityInfo(cityName:String,country:String = "Singapore",lat:Double = 40.95,
                         long:Double = 60.71):CityInfo{
        val areaName = listOf(entry(cityName))
        val country = listOf(entry(country))
        val region = listOf(empty())

        return CityInfo(areaName,country,region,lat,long)
    }

    private fun entry(content:String): CityEntry{
        val entry = mapOf("value" to content)
        return CityEntry(entry)
    }

    private fun empty(): CityEntry{
        return CityEntry(emptyMap())
    }
}