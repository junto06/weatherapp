package com.weatherapp.mockfactory

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.dto.*

/*
    Test Factory for Mock response
 */
object CityMockResponseFactory{
    fun errorResponse():SearchResponse{
        val entry = ErrorEntry(mapOf("msg" to  "Unable to find any matching weather location to the query submitted!"))
        val errorResult  = ErrorResultDTO(listOf(entry))
        return SearchResponse(errorResult = errorResult)
    }

    fun searchResponse():SearchResponse{
        val list = CityMockFactory.generateCities()
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
}