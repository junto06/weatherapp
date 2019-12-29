package com.weatherapp.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.weatherapp.data.model.City
import com.weatherapp.data.repo.DataSource
import com.weatherapp.data.storage.CityDao
import com.weatherapp.di.DaggerTestComponent
import com.weatherapp.di.MockRemoteModule
import com.weatherapp.mockfactory.MockResponseFactory
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

/*
    This is integration test
 */

class LoadCityWeatherTest{
    //subject under test
    private lateinit var weatherRepo: WeatherRepoImp

    @Inject
    lateinit var dataSource: DataSource

    @Inject
    lateinit var cityDao: CityDao

    @Before
    fun testSetup(){
        val response = MockResponseFactory.mockWeatherResponse()
        val testComponent = DaggerTestComponent.builder().mockRemoteModule(MockRemoteModule(response)).build()
        testComponent.inject(this)
        weatherRepo = WeatherRepoImp(dataSource,cityDao)
    }

    @Test
    fun loadCityWeather_shouldUpdateDBAndReturnWeather() {
        val city = City("New York","United States","",40.9,90.981)

        //database should have zero entries
        assertThat(cityDao.getRecentCities().blockingFirst().size).isEqualTo(0)
        val defaultTime = city.accessTime

        var testObserver = weatherRepo.loadCityWeather(city).test()
        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val list = cityDao.getRecentCities().blockingFirst()
        //db should have one entry
        assertThat(list.size).isEqualTo(1)

        //now access time should be greater than default time
        assertThat(city.accessTime > defaultTime).isTrue()


    }

}