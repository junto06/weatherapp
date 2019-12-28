package com.weatherapp.data.repo.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(@SerializedName("data") val result:WeatherResponseDTO)

data class WeatherResponseDTO(@SerializedName("current_condition")
                              private val current:List<CurrentConditionDTO>){

    fun currentWeather():CurrentConditionDTO = current[0]

}

data class CurrentConditionDTO(val observation_time:String,val temp_C:Int,val temp_F:Int,
                               val humidity:Int,private val weatherIconUrl:List<WeatherEntry>,
                               private val weatherDesc:List<WeatherEntry>){

    fun weatherIconUrl():String = weatherIconUrl[0].value

    fun weatherDesc():String = weatherDesc[0].value

}

class WeatherEntry(entry: Map<String,String>){
    internal var value = ""

    init {
        if(entry.containsKey("value")){
            value = requireNotNull(entry["value"])
        }
    }

    override fun toString(): String = "value=$value"
}

