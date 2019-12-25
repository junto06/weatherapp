package com.weatherapp.data.repo.remote.dto

data class SearchResponse(val search_api:SearchResult)


data class SearchResult(val result:List<CityInfo>)


data class CityInfo(val areaName:List<CityEntry>,val country:List<CityEntry>,
                    val region:List<CityEntry>)


class CityEntry(list: List<Map<String,String>>){
    var value = ""

    init {
        if(list.isNotEmpty()){
            value = requireNotNull(list[0]["value"])
        }
    }

    override fun toString(): String = "(value=$value)"
}