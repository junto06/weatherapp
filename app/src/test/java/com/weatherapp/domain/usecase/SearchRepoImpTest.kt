package com.weatherapp.domain.usecase

import com.google.common.truth.Truth
import com.weatherapp.data.model.SearchResult
import com.weatherapp.data.repo.CityDataSource
import com.weatherapp.mockfactory.CityMockFactory
import com.weatherapp.mockfactory.CityMockResponseFactory
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchRepoImpTest {

    @Mock
    lateinit var cityDataSource: CityDataSource

    //subject under test
    lateinit var searchRepo: SearchRepoImp

    @Before
    fun testSetup(){
        MockitoAnnotations.initMocks(this)
        searchRepo = SearchRepoImp(cityDataSource)
    }

    @Test
    fun searchCity_shouldReturnFilteredList() {
        //search cities start with
        val cityName = "Se"

        val cities = CityMockResponseFactory.searchResult(listOf("Senkang","Serangoon","Punggol"))

        `when`(cityDataSource.searchCity(anyString())).thenReturn(Observable.just(cities))

        //test
        var testObserver = searchRepo.searchCity(cityName).test()

        //assert
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
    fun searchCity_shouldReturnEmptyList() {
        //search cities start with
        val cityName = "xyz"

        val cities = CityMockResponseFactory.searchResult(listOf("Senkang","Serangoon","Punggol"))

        `when`(cityDataSource.searchCity(anyString())).thenReturn(Observable.just(cities))

        //test
        var testObserver = searchRepo.searchCity(cityName).test()

        //assert
        testObserver.awaitTerminalEvent()
        testObserver.assertValueCount(1)
        testObserver.assertNoErrors()
        testObserver.assertComplete()

        val result = testObserver.values()[0] as SearchResult

        Truth.assertThat(result.hasError).isFalse()

        Truth.assertThat(result.errorMessage).isEmpty()

        Truth.assertThat(result.cityList).isEmpty()
    }
}