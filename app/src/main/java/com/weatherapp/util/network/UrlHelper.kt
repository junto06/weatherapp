package com.weatherapp.util.network

import java.net.URL

object UrlHelper{
    fun isValidUrl(baseUrl:String):Boolean{
        if(baseUrl.isNotEmpty()){
            try {
                val validURI = URL(baseUrl).toURI()
                return validURI != null
            }catch (ex:Exception){}
        }
        return false
    }
}