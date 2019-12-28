package com.weatherapp.data.model

data class CurrentWeather(val temperatureCelsius:Int, val temperatureFahrenheit:Int,val humidity:Int,
                          val observationTime:String,val currentWeather:String,val weatherIconUrl:String)