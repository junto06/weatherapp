package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.dto.CityInfo
import com.weatherapp.data.repo.remote.dto.SearchResponse
import javax.inject.Inject

/*
    City DTO to domain entity mapper
 */
class CityMapperImp @Inject constructor():CityMapper{

    override fun map(searchResponse: SearchResponse): SearchResult {
        return if(searchResponse.searchResults != null){
            val mutableList = mutableListOf<City>()
            var mapList = map(searchResponse.searchResults!!.result)
            mutableList.addAll(mapList)
            SearchResult(mutableList)
        }else{
            SearchResult(listOf(),true,searchResponse.errorResult!!.errorMessage())
        }
    }

    override fun map(list: List<CityInfo>): List<City> {
        return list.map {
            map(it)
        }.toList()
    }

    override fun map(cityInfo: CityInfo): City {
        return City(cityInfo.areaName(),cityInfo.country(),cityInfo.region(),cityInfo.latitude,cityInfo.longitude)
    }

}