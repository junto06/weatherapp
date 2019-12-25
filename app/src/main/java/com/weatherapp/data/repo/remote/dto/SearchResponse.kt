package com.weatherapp.data.repo.remote.dto

data class SearchResponse(val search_api:SearchResult)


data class SearchResult(val result:List<CityInfo>)


class CityInfo(areaList:List<CityEntry>,countryList:List<CityEntry>,
                    regionList:List<CityEntry>){

    var areaName = ""
    var country = ""
    var region = ""

    init {
        areaName = areaList[0].value
        country = countryList[0].value
        region = regionList[0].value
    }
}


class CityEntry(list: List<Map<String,String>>){
    var value = ""

    init {
        if(list.isNotEmpty()){
            value = requireNotNull(list[0]["value"])
        }
    }

    override fun toString(): String = "(value=$value)"
}