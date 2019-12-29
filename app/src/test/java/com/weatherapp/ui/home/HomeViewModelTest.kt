package com.weatherapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.weatherapp.R
import com.weatherapp.data.storage.CityDao
import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.mockfactory.CityMockFactory
import com.weatherapp.mockfactory.MockResponseFactory
import com.weatherapp.util.Event
import com.weatherapp.util.LiveDataTestUtil.getLiveData
import com.weatherapp.util.scheduler.IScheduler
import com.weatherapp.util.scheduler.TestScheduler
import io.reactivex.Observable
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.Exception

class HomeViewModelTest {

    //subject under test
    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var searchRepo: SearchRepo

    private lateinit var scheduler: IScheduler

    @Mock
    private lateinit var cityDao: CityDao

    //run async operation in synchronous way
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupTest(){
        MockitoAnnotations.initMocks(this)

        scheduler = TestScheduler()

        //setup mock for initialization
        val cities = CityMockFactory.generateCitiesList("United States")
        `when`(cityDao.getRecentCities()).thenReturn(Observable.just(cities))

        homeViewModel = HomeViewModel(searchRepo,scheduler,cityDao)
    }

    @Test
    fun search_shouldReturnSearchResult() {
        val list = listOf("Senkang","Serangoon")

        val searchResult = MockResponseFactory.searchResult(list)


        //setup mock
        `when`(searchRepo.searchCity(anyString())).thenReturn(Observable.just(searchResult))

        val searchWord = "Se"

        //test
        homeViewModel.search(searchWord)

        val data = getLiveData(homeViewModel.data)

        assertThat(data).isNotNull()

        assertThat(data).isNotEmpty()

        assertThat(data.size).isEqualTo(2)
    }

    @Test
    fun search_errorResult_shouldReturnErrorResult() {

        val searchResult = MockResponseFactory.errorResult("city not found")

        `when`(searchRepo.searchCity(anyString())).thenReturn(Observable.just(searchResult))

        val searchWord = "abcz"

        //test
        homeViewModel.search(searchWord)

        val error = getLiveData(homeViewModel.error)

        assertThat(error).isNotNull()

        val event = error as Event<String>

        val result = event.getEventData()

        assertThat(result).isNotEmpty()

        assertThat(result).isEqualTo("city not found")
    }

    @Test
    fun search_errorResultException_shouldReturnErrorResult() {

        `when`(searchRepo.searchCity(anyString())).
            thenReturn(Observable.error(Exception("Mock exception")))

        val searchWord = "ab"

        //test
        homeViewModel.search(searchWord)

        val error = getLiveData(homeViewModel.error)

        assertThat(error).isNotNull()

        val event = error as Event<Int>

        val result = event.getEventData()

        assertThat(result).isEqualTo(R.string.error_loading_data)
    }

    @Test
    fun loadRecentCities_shouldHaveData(){

        //should have data on initialization

        val data = getLiveData(homeViewModel.data)

        assertThat(data).isNotNull()

        assertThat(data).isNotEmpty()

        assertThat(data.size).isEqualTo(2)
    }

    @Test
    fun refreshOnCityDetails(){

        //list should be sorted based on access time

        //choose 2nd item from list so it can be on the top after refresh
        var data = getLiveData(homeViewModel.data)

        var city = data[1]

        //first access time should be zero as its a new item
        assertThat(city.accessTime).isEqualTo(0)
        //choose city name should also match
        assertThat(city.name).isEqualTo("Serangoon")

        //test
        homeViewModel.refreshOnCityDetails(city)

        assertThat(city.accessTime > 0).isTrue()

        data = getLiveData(homeViewModel.data)

        assertThat(data).isNotNull()

        assertThat(data).isNotEmpty()

        assertThat(data.size).isEqualTo(2)

        assertThat(data[0].name).isEqualTo("Serangoon")

        assertThat(data[1].name).isEqualTo("Punggol")
    }
}