package com.weatherapp.mockfactory

import com.weatherapp.data.repo.remote.dto.CityEntry
import com.weatherapp.data.repo.remote.dto.CityInfo

/*
    This factory class generate Mock data for testing purpose
 */
object CityMockFactory{

    fun generateCities():List<CityInfo>{
        return mutableListOf(generateCity("Punggol"),generateCity("Serangoon"))
    }

    fun generateCity(cityName:String):CityInfo{
        val areaName = listOf(entry(cityName))
        val country = listOf(entry("Singapore"))
        val region = listOf(empty())

        return CityInfo(areaName,country,region)
    }

    private fun entry(content:String): CityEntry{
        val entry = mapOf("value" to content)
        return CityEntry(listOf(entry))
    }

    private fun empty(): CityEntry{
        return CityEntry(listOf())
    }
}