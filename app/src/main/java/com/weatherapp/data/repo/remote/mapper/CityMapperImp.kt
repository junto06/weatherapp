package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.City
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.dto.CityInfo
import com.weatherapp.data.repo.remote.dto.SearchResponse
import javax.inject.Inject

class CityMapperImp @Inject constructor():CityMapper{

    override fun map(searchResponse: SearchResponse): SearchResult {
        return if(searchResponse.search_api != null){
            SearchResult(map(searchResponse.search_api!!.result))
        }else{
            SearchResult(listOf(),true,searchResponse.errorResult!!.data.errorMessage)
        }
    }

    override fun map(list: List<CityInfo>): List<City> {
        return list.map {
            map(it)
        }.toList()
    }

    override fun map(cityInfo: CityInfo): City {
        return City(cityInfo.areaName,cityInfo.country,cityInfo.region)
    }

}