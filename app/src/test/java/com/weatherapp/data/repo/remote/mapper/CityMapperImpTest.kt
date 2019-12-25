package com.weatherapp.data.repo.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.weatherapp.mockfactory.CityMockFactory
import org.junit.Test

import org.junit.Before

class CityMapperImpTest {

    lateinit var cityMapper: CityMapper

    @Before
    fun testSetup(){
        cityMapper = CityMapperImp()
    }

    @Test
    fun map_cityInfoList_shouldMapToCityList() {
        val citiesInfo = CityMockFactory.generateCities()

        val cities = cityMapper.map(citiesInfo)

        var i = 0

        cities.forEach {

            val cityInfo = citiesInfo[i++]

            it.apply {
                assertThat(name).isNotEmpty()
                assertThat(name).isEqualTo(cityInfo.areaName)

                assertThat(country).isNotEmpty()
                assertThat(country).isEqualTo(cityInfo.country)

                assertThat(region).isEqualTo(cityInfo.region)
            }
        }
    }

    @Test
    fun map_cityInfo_shouldMatchCity() {
        val cityInfo = CityMockFactory.generateCity("Senkang")

        val city = cityMapper.map(cityInfo)

        city.apply {
            assertThat(name).isNotEmpty()
            assertThat(name).isEqualTo(cityInfo.areaName)

            assertThat(country).isNotEmpty()
            assertThat(country).isEqualTo(cityInfo.country)

            assertThat(region).isEqualTo(cityInfo.region)
        }
    }
}