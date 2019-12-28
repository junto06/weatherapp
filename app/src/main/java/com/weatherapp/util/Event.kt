package com.weatherapp.util

class Event<T>(private val data:T){

    private var isEventHandled = false

    fun getEventData():T?{
        return if(!isEventHandled){
            isEventHandled = true
            data
        }else{
            null
        }
    }

}