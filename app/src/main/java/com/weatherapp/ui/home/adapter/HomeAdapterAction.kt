package com.weatherapp.ui.home.adapter

import com.weatherapp.data.model.City

interface HomeAdapterAction{
    fun onCityDetails(item:City)
}