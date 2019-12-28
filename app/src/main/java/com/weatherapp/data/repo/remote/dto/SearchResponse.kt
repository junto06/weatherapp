package com.weatherapp.data.repo.remote.dto

import com.google.gson.annotations.SerializedName

/*
    SearchResponse is Data Transfer class which contains response.
    Response cold be either search result or error.
    @param searchResults is for search results
    @param errorResult is mapped in case of error response
 */

data class SearchResponse(@SerializedName("search_api") var searchResults:SearchResultDTO?=null,
                          @SerializedName("data") var errorResult:ErrorResultDTO?=null)

data class SearchResultDTO(val result:List<CityInfo>)


data class CityInfo(private val areaName:List<CityEntry>, private val country:List<CityEntry>,
                    private val region:List<CityEntry>){

    fun areaName():String = areaName[0].value

    fun country():String = country[0].value

    fun region():String = region[0].value
}


class CityEntry(entry: Map<String,String>){
    var value = ""

    init {
        if(entry.containsKey("value")){
            value = requireNotNull(entry["value"])
        }
    }

    override fun toString(): String = "value=$value"
}


data class ErrorResultDTO(@SerializedName("error") private val error:List<ErrorEntry>){

    fun errorMessage():String = error[0].msg

}


class ErrorEntry(entry: Map<String,String>){
    internal var msg = ""

    init {
        if(entry.containsKey("msg")){
            msg = requireNotNull(entry["msg"])
        }
    }

    override fun toString(): String = "(msg=$msg)"
}