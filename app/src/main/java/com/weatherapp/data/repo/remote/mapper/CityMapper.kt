package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.City
import com.weatherapp.data.repo.remote.dto.CityInfo

interface CityMapper{
    fun map(list: List<CityInfo>):List<City>

    fun map(cityInfo: CityInfo):City
}