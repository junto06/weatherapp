package com.weatherapp.data.model

data class SearchResult(val cityList:List<City>,val hasError:Boolean = false,
                        val errorMessage:String = "")