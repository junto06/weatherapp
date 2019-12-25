package com.weatherapp.mockfactory

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.dto.*

/*
    Mock response
 */
object CityMockResponseFactory{
    fun errorResponse():SearchResponse{
        val entry = ErrorEntry(listOf(mapOf("msg" to  "Unable to find any matching weather location to the query submitted!")))
        val errorResult  = ErrorResultDTO(ErrorDTO(listOf(entry)))
        return SearchResponse(errorResult = errorResult)
    }

    fun searchResponse():SearchResponse{
        val list = CityMockFactory.generateCities()
        val searchResult = SearchResultDTO(list)
        return SearchResponse(search_api = searchResult)
    }

    fun searchResult(list:List<String>):SearchResult{
        val cities = mutableListOf<City>()

        for(city in list){
            cities.add(CityMockFactory.generateCity(city))
        }

        return SearchResult(cities)
    }
}