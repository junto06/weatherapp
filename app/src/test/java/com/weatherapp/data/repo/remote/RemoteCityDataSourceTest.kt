package com.weatherapp.data.repo.remote

import com.google.common.truth.Truth.assertThat
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.mockfactory.CityMockFactory
import com.weatherapp.mockfactory.CityMockResponseFactory
import com.weatherapp.util.network.HttpConfig
import io.reactivex.Observable
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.Exception

/*
    Unit test of RemoteCityDataSource class
    class dependencies are mocked
 */
class RemoteCityDataSourceTest {

    @Mock
    lateinit var httpConfig: HttpConfig

    @Mock
    lateinit var weatherApi: WeatherApi

    @Mock
    lateinit var cityMapper: CityMapper

    //subject under test
    lateinit var cityDataSource:RemoteCityDataSource


    @Before
    fun testSetup(){
        MockitoAnnotations.initMocks(this)
        cityDataSource = RemoteCityDataSource(httpConfig,weatherApi,cityMapper)
    }

    @Test
    fun searchCity_ShouldReturnSearchResult() {
        //setup dependencies
        val response = CityMockResponseFactory.searchResponse()

        val cities = CityMockFactory.generateCitiesList()

        `when`(httpConfig.apiKey()).thenReturn("xyz")

        `when`(httpConfig.format()).thenReturn("json")

        `when`(cityMapper.map(response)).thenReturn(SearchResult(cities))

        `when`(weatherApi.searchCity(anyString(),anyString(),anyString())).
            thenReturn(Observable.just(response))

        //test
        var testObserver = cityDataSource.searchCity("Senkang").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as SearchResult

        assertThat(result.hasError).isFalse()

        assertThat(result.errorMessage).isEmpty()

        assertThat(result.cityList).isNotEmpty()

        assertThat(result.cityList.size).isEqualTo(cities.size)
    }

    @Test
    fun searchCity_CityNotFound_ShouldReturnErrorResponse() {
        //setup dependencies
        val response = CityMockResponseFactory.errorResponse()

        `when`(httpConfig.apiKey()).thenReturn("xyz")

        `when`(httpConfig.format()).thenReturn("json")

        `when`(cityMapper.map(response)).thenReturn(SearchResult(listOf(),true,"Not found...m6i8"))

        `when`(weatherApi.searchCity(anyString(),anyString(),anyString())).thenReturn(Observable.just(response))

        //test
        var testObserver = cityDataSource.searchCity("Serangoon").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as SearchResult

        assertThat(result.hasError).isTrue()

        assertThat(result.errorMessage).isNotEmpty()

        assertThat(result.cityList).isEmpty()
    }

    @Test
    fun searchCity_OnError_ShouldReturnSearchResult() {
        //setup dependencies

        `when`(httpConfig.apiKey()).thenReturn("xyz")

        `when`(httpConfig.format()).thenReturn("json")

        `when`(weatherApi.searchCity(anyString(),anyString(),anyString())).
            thenReturn(Observable.error(Exception("Mock exception!!")))

        //test
        var testObserver = cityDataSource.searchCity("Senkang").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(0)
        testObserver.assertError(Exception::class.java)
        testObserver.assertNotComplete()
    }
}