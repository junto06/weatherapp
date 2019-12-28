package com.weatherapp.mockfactory

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.dto.*

/*
    Test Factory for Mock response
 */
object MockResponseFactory{
    fun errorResponse():SearchResponse{
        val entry = ErrorEntry(mapOf("msg" to  "Unable to find any matching weather location to the query submitted!"))

        val errorResult  = ErrorResultDTO(listOf(entry))

        return SearchResponse(errorResult = errorResult)
    }

    fun searchResponse():SearchResponse{
        val list = CityMockFactory.generateCities()

        return searchResponse(list)
    }

    fun searchResponse(list:List<CityInfo>):SearchResponse{
        val searchResult = SearchResultDTO(list)

        return SearchResponse(searchResults = searchResult)
    }

    fun searchResult(list:List<String>):SearchResult{
        val cities = mutableListOf<City>()

        for(city in list){
            cities.add(CityMockFactory.generateCity(city))
        }

        return SearchResult(cities)
    }

    fun errorResult(errorMessage:String):SearchResult{
        return SearchResult(emptyList(),true,errorMessage)
    }

    fun mockWeatherResponse():CurrentWeatherResponse{

        val weatherIcon = WeatherEntry(mapOf("value" to "http://weather.com/weather.png"))

        val weatherDescription = WeatherEntry(mapOf("value" to "Haze"))

        val weather = CurrentConditionDTO("01:53 PM",7,45,82,
            listOf(weatherIcon), listOf(weatherDescription))

        val dto = WeatherResponseDTO(listOf(weather))

        return CurrentWeatherResponse(dto)
    }
}