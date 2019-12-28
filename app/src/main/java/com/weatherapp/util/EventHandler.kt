package com.weatherapp.util

import androidx.lifecycle.Observer

/*
    EventHandler is used to handle one time events.
 */
class EventHandler<T>(private val handler:(T)->Unit):Observer<Event<T>>{
    override fun onChanged(t: Event<T>?) {
        t?.getEventData()?.let {
            handler(it)
        }
    }

}