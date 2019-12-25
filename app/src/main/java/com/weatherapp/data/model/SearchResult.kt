package com.weatherapp.data.model

data class SearchResult(var cityList:List<City>,val hasError:Boolean = false,
                        val errorMessage:String = "")