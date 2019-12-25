package com.weatherapp.data.repo.remote.mapper

import com.weatherapp.data.model.City
import com.weatherapp.data.repo.remote.dto.CityInfo

class CityMapperImp:CityMapper{

    override fun map(list: List<CityInfo>): List<City> {
        return list.map {
            map(it)
        }.toList()
    }

    override fun map(cityInfo: CityInfo): City {
        return City(cityInfo.areaName,cityInfo.country,cityInfo.region)
    }

}