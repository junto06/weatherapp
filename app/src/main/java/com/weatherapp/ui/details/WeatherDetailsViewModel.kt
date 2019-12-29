package com.weatherapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weatherapp.R
import com.weatherapp.data.model.City
import com.weatherapp.data.model.CurrentWeather
import com.weatherapp.domain.usecase.WeatherRepo
import com.weatherapp.util.EspressonResourceIdling
import com.weatherapp.util.Event
import com.weatherapp.util.android.imageloading.ImageLoader
import com.weatherapp.util.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherDetailsViewModel @Inject constructor(private val weatherRepo: WeatherRepo,
                                                  private val scheduler: IScheduler,
                                                  val imageLoader: ImageLoader):ViewModel(){

    //error state
    private val _error = MutableLiveData<Event<Int>>()
    val error: MutableLiveData<Event<Int>> = _error

    private val compositeDisposable = CompositeDisposable()

    private val _data = MutableLiveData<CurrentWeather>()
    val data:MutableLiveData<CurrentWeather> = _data

    private val _city = MutableLiveData<City>()
    val city:MutableLiveData<City> = _city


    //loading state
    private var _loading:Boolean = false

    fun loadWeather(city:City){

        if(_loading ){
            return
        }

        _loading = true

        this.city.value = city

        EspressonResourceIdling.idle(false)

        val disposable = weatherRepo.loadCityWeather(city)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.main())
            .subscribe({
                EspressonResourceIdling.idle(true)
                _data.value = it
            },{
                EspressonResourceIdling.idle(true)
                _error.value = Event(R.string.error_loading_weather_data)
            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}