package com.weatherapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.weatherapp.data.model.City
import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.repo.DataSource
import io.reactivex.Observable
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class WeatherInfoImpTest {

    @Mock
    lateinit var dataSource: DataSource

    //subject under test
    private lateinit var weatherRepo: WeatherRepoImp

    @Before
    fun testSetup(){
        MockitoAnnotations.initMocks(this)
        weatherRepo = WeatherRepoImp(dataSource)
    }


    @Test
    fun loadCityWeather_shouldReturnWeather() {

        val weather = CurrentWeather(7,45,82,
            "01:53 PM","Haze","http://weather.com/weather.png")

        `when`(dataSource.getCurrentWeather(anyString())).thenReturn(Observable.just(weather))

        //test
        val city = City("New York","United States","",40.9,90.981)

        var testObserver = weatherRepo.loadCityWeather(city).test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as CurrentWeather

        result.apply {
            assertThat(observationTime).isEqualTo("01:53 PM")
            assertThat(temperatureCelsius).isEqualTo(7)
            assertThat(temperatureFahrenheit).isEqualTo(45)
            assertThat(humidity).isEqualTo(82)
            assertThat(weatherIconUrl).isEqualTo("http://weather.com/weather.png")
            assertThat(currentWeather).isEqualTo("Haze")
        }
    }
}