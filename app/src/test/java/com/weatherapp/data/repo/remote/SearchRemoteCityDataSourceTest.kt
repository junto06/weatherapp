package com.weatherapp.data.repo.remote

import com.google.common.truth.Truth
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.remote.api.WeatherApi
import com.weatherapp.data.repo.remote.dto.SearchResponse
import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.di.DaggerTestComponent
import com.weatherapp.di.MockRemoteModule
import com.weatherapp.mockfactory.CityMockResponseFactory
import com.weatherapp.util.network.HttpConfig
import org.junit.Test
import java.lang.Exception

import javax.inject.Inject

/*
    SearchRemoteCityDataSourceTest is integration test
 */
class SearchRemoteCityDataSourceTest {

    @Inject
    lateinit var httpConfig: HttpConfig

    @Inject
    lateinit var weatherApi: WeatherApi

    @Inject
    lateinit var cityMapper: CityMapper

    //subject under test
    lateinit var cityDataSource:RemoteCityDataSource

    //setup dependency
    private fun testSetup(response: SearchResponse?){
        val testComponent = DaggerTestComponent.builder().mockRemoteModule(MockRemoteModule(response)).build()
        testComponent.inject(this)
        cityDataSource = RemoteCityDataSource(httpConfig,weatherApi,cityMapper)
    }

    @Test
    fun searchCity_ShouldReturnSearchResult() {
        //setup dependencies
        val response = CityMockResponseFactory.searchResponse()
        testSetup(response)

        //test searchCity
        var testObserver = cityDataSource.searchCity("Punggol").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as SearchResult

        Truth.assertThat(result.hasError).isFalse()

        Truth.assertThat(result.errorMessage).isEmpty()

        Truth.assertThat(result.cityList).isNotEmpty()

        Truth.assertThat(result.cityList.size).isEqualTo(2)
    }

    @Test
    fun searchCity_CityNotFound_ShouldReturnErrorResponse() {
        //setup dependencies
        val response = CityMockResponseFactory.errorResponse()
        testSetup(response)

        //test searchCity
        var testObserver = cityDataSource.searchCity("Senkang").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as SearchResult

        Truth.assertThat(result.hasError).isTrue()

        Truth.assertThat(result.errorMessage).isNotEmpty()

        Truth.assertThat(result.cityList).isEmpty()
    }

    @Test
    fun searchCity_OnError_ShouldReturnSearchResult() {
        //setup dependencies
        testSetup(null)

        //test searchCity
        var testObserver = cityDataSource.searchCity("Senkang").test()

        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(0)
        testObserver.assertError(Exception::class.java)
        testObserver.assertNotComplete()
    }
}