package com.weatherapp.data.repo.remote.dto

data class SearchResponse(var search_api:SearchResultDTO?=null,var errorResult:ErrorResultDTO?=null)

data class ErrorResultDTO(val data:ErrorDTO)

data class ErrorDTO(val error:List<ErrorEntry>){
    var errorMessage = ""

    init {
        errorMessage = error[0].msg
    }
}


data class SearchResultDTO(val result:List<CityInfo>)


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
    internal var value = ""

    init {
        if(list.isNotEmpty()){
            value = requireNotNull(list[0]["value"])
        }
    }

    override fun toString(): String = "(value=$value)"
}

class ErrorEntry(list: List<Map<String,String>>){
    internal var msg = ""

    init {
        if(list.isNotEmpty()){
            msg = requireNotNull(list[0]["msg"])
        }
    }

    override fun toString(): String = "(msg=$msg)"
}