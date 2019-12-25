package com.weatherapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.mockfactory.CityMockResponseFactory
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

class HomeViewModelTest {

    //subject under test
    lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var searchRepo: SearchRepo

    private lateinit var scheduler: IScheduler

    //run async operation in synchronous way
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupTest(){
        MockitoAnnotations.initMocks(this)

        scheduler = TestScheduler()

        homeViewModel = HomeViewModel(searchRepo,scheduler)
    }

    @Test
    fun search_shouldReturnSearchResult() {
        val list = listOf("Senkang","Serangoon")

        val searchResult = CityMockResponseFactory.searchResult(list)

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

        val searchResult = CityMockResponseFactory.errorResult("city not found")

        `when`(searchRepo.searchCity(anyString())).thenReturn(Observable.just(searchResult))

        val searchWord = "abcz"

        //test
        homeViewModel.search(searchWord)

        val error = getLiveData(homeViewModel.error)

        assertThat(error).isNotNull()

        val result = error as String

        assertThat(result).isNotEmpty()

        assertThat(result).isEqualTo("city not found")
    }
}