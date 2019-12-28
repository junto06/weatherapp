package com.weatherapp.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.weatherapp.R
import com.weatherapp.data.model.City
import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.domain.usecase.WeatherRepo
import com.weatherapp.util.Event
import com.weatherapp.util.LiveDataTestUtil
import com.weatherapp.util.scheduler.IScheduler
import com.weatherapp.util.scheduler.TestScheduler
import io.reactivex.Observable
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.lang.Exception

class WeatherDetailsViewModelTest {

    //subject under test
    lateinit var weatherViewModel:WeatherDetailsViewModel

    @Mock
    lateinit var weatherRepo: WeatherRepo

    private lateinit var scheduler: IScheduler

    //run async operation in synchronous way
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupTest(){
        MockitoAnnotations.initMocks(this)

        scheduler = TestScheduler()

        weatherViewModel = WeatherDetailsViewModel(weatherRepo,scheduler)
    }

    @Test
    fun loadWeather_shouldLoadWeather() {

        val weather = CurrentWeather(7,45,82,
            "01:53 PM","Haze","http://weather.com/weather.png")

        val city = City("New York","United States","",40.9,90.981)

        //setup mock
        `when`(weatherRepo.loadCityWeather(city)).thenReturn(Observable.just(weather))

        //test

        weatherViewModel.loadWeather(city)

        val data = LiveDataTestUtil.getLiveData(weatherViewModel.data)

        data.apply {
            assertThat(observationTime).isEqualTo("01:53 PM")
            assertThat(temperatureCelsius).isEqualTo(7)
            assertThat(temperatureFahrenheit).isEqualTo(45)
            assertThat(humidity).isEqualTo(82)
            assertThat(weatherIconUrl).isEqualTo("http://weather.com/weather.png")
            assertThat(currentWeather).isEqualTo("Haze")
        }
    }

    @Test
    fun loadWeather_onError_shouldGiveError() {

        //setup dependencies

        val city = City("New York","United States","",40.9,90.981)

        `when`(weatherRepo.loadCityWeather(city)).thenReturn(Observable.error(Exception("Mock exception")))

        //test
        weatherViewModel.loadWeather(city)

        val error = LiveDataTestUtil.getLiveData(weatherViewModel.error)

        assertThat(error).isNotNull()

        val event = error as Event<Int>

        val result = event.getEventData()

        assertThat(result).isEqualTo(R.string.error_loading_weather_data)
    }
}