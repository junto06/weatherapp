package com.weatherapp.data.repo.remote

import com.google.common.truth.Truth
import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.CurrentWeatherResponse
import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.data.repo.remote.mapper.WeatherMapper
import com.weatherapp.di.DaggerTestComponent
import com.weatherapp.di.MockRemoteModule
import com.weatherapp.mockfactory.MockResponseFactory
import com.weatherapp.util.network.HttpConfig
import org.junit.Test
import java.lang.Exception
import javax.inject.Inject

class CurrentWeatherRemoteDataSourceTest {

    @Inject
    lateinit var httpConfig: HttpConfig

    @Inject
    lateinit var weatherApi: WeatherApi

    @Inject
    lateinit var cityMapper: CityMapper

    @Inject
    lateinit var weatherMapper: WeatherMapper

    //subject under test
    lateinit var dataSource:RemoteDataSource

    private fun testSetup(response: CurrentWeatherResponse?){
        val testComponent = DaggerTestComponent.builder().mockRemoteModule(MockRemoteModule(response)).build()
        testComponent.inject(this)
        dataSource = RemoteDataSource(httpConfig,weatherApi,cityMapper,weatherMapper)
    }

    @Test
    fun getCurrentWeather_ShouldReturnWeather() {
        //setup dependencies
        val response = MockResponseFactory.mockWeatherResponse()

        testSetup(response)

        //test get weather
        val testObserver = dataSource.getCurrentWeather("40.9,90.981").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as CurrentWeather

        result.apply {
            Truth.assertThat(observationTime).isEqualTo("01:53 PM")
            Truth.assertThat(temperatureCelsius).isEqualTo(7)
            Truth.assertThat(temperatureFahrenheit).isEqualTo(45)
            Truth.assertThat(humidity).isEqualTo(82)
            Truth.assertThat(weatherIconUrl).isEqualTo("http://weather.com/weather.png")
            Truth.assertThat(currentWeather).isEqualTo("Haze")
        }
    }

    @Test
    fun getCurrentWeather_OnNetworkError_ShouldReturnError() {
        //setup dependencies
        testSetup(null)

        //test get weather
        var testObserver = dataSource.getCurrentWeather("40.9,90.981").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(0)
        testObserver.assertError(Exception::class.java)
        testObserver.assertNotComplete()
    }
}