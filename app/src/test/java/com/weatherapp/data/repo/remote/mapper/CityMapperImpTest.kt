package com.weatherapp.data.repo.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.weatherapp.di.DaggerTestComponent
import com.weatherapp.mockfactory.CityMockFactory
import com.weatherapp.mockfactory.CityMockResponseFactory
import org.junit.Test

import org.junit.Before
import javax.inject.Inject

class CityMapperImpTest {

    //subject under test
    @Inject
    lateinit var cityMapper: CityMapper

    @Before
    fun testSetup(){
        val testComponent = DaggerTestComponent.create()
        testComponent.inject(this)
    }

    @Test
    fun mapSearchResponse_errorResponse_shouldMapToSearchResult() {
        val errorResponse = CityMockResponseFactory.errorResponse()

        val response = cityMapper.map(errorResponse)

        assertThat(response.hasError).isTrue()

        assertThat(response.errorMessage).isNotEmpty()
    }

    @Test
    fun map_searchResponse_shouldMapToSearchResult() {
        val searchResponse = CityMockResponseFactory.searchResponse()

        val response = cityMapper.map(searchResponse)

        assertThat(response.hasError).isFalse()

        assertThat(response.errorMessage).isEmpty()

        assertThat(response.cityList).isNotEmpty()

        assertThat(response.cityList.size).isEqualTo(2)
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
        val cityInfo = CityMockFactory.generateCityInfo("Senkang")

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