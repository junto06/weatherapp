package com.weatherapp.util.network

interface HttpConfig{
    fun baseUrl():String
    fun apiKey():String
    fun format():String = "json"
}