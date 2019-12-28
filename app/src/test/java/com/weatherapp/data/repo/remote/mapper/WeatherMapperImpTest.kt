package com.weatherapp.data.repo.remote.mapper

import com.google.common.truth.Truth.assertThat
import com.weatherapp.mockfactory.MockResponseFactory
import org.junit.Test

import org.junit.Before

class WeatherMapperImpTest {

    //subject under test
    private lateinit var weatherMapper: WeatherMapper

    @Before
    fun testSetup(){
        weatherMapper = WeatherMapperImp()
    }

    @Test
    fun map_shouldConvert_WeatherDTO_TOWeatherEntity() {
        val mockResponse = MockResponseFactory.mockWeatherResponse()

        val weather = weatherMapper.map(mockResponse)

        weather.apply {
            assertThat(observationTime).isEqualTo("01:53 PM")
            assertThat(temperatureCelsius).isEqualTo(7)
            assertThat(temperatureFahrenheit).isEqualTo(45)
            assertThat(humidity).isEqualTo(82)
            assertThat(weatherIconUrl).isEqualTo("http://weather.com/weather.png")
            assertThat(currentWeather).isEqualTo("Haze")
        }
    }
}