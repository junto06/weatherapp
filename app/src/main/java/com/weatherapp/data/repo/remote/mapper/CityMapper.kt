package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.dto.CityInfo
import com.weatherapp.data.repo.remote.dto.SearchResponse

interface CityMapper{

    fun map(searchResponse: SearchResponse): SearchResult

    fun map(list: List<CityInfo>):List<City>

    fun map(cityInfo: CityInfo):City
}