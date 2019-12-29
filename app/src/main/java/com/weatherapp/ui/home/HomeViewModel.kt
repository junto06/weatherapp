package com.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weatherapp.R
import com.weatherapp.data.model.City
import com.weatherapp.data.storage.CityDao
import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.util.EspressonResourceIdling
import com.weatherapp.util.Event
import com.weatherapp.util.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val searchRepo: SearchRepo,
                    private val scheduler:IScheduler,private val cityDao:CityDao):ViewModel(){

    private val subject:PublishSubject<String> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()

    private val _data = MutableLiveData<List<City>>()
    val data:MutableLiveData<List<City>> = _data

    //error indicating error state
    private val _error = MutableLiveData<Event<Any>>()
    val error:MutableLiveData<Event<Any>> = _error

    init {
        setupSearch()
        loadRecentCities()
    }

    fun loadRecentCities() {
        EspressonResourceIdling.idle(false)
        val disposable =
            cityDao.getRecentCities()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .subscribe({
                    EspressonResourceIdling.idle(true)
                    _data.value = orderByRecent(it)
                },{
                    EspressonResourceIdling.idle(true)
                })

        compositeDisposable.add(disposable)
    }

    fun search(query:String){
        subject.onNext(query)
        EspressonResourceIdling.idle(false)
    }

    private fun setupSearch(){
        val disposable = subject.debounce(400,TimeUnit.MILLISECONDS)
            .filter {

                if(it.isEmpty()){
                    EspressonResourceIdling.idle(true)
                }
                it.isNotEmpty()

            }
            .distinctUntilChanged()
            .switchMap ({

                searchRepo.searchCity(it)
                    .subscribeOn(scheduler.io())

            },1)
            .observeOn(scheduler.main())
            .subscribe ({

                EspressonResourceIdling.idle(true)

                if(it.hasError){
                    _error.value = Event(it.errorMessage)
                }else{
                    _data.value = it.cityList
                }

            },{

                EspressonResourceIdling.idle(true)
                _error.value = Event(R.string.error_loading_data)

            })

        compositeDisposable.add(disposable)
    }

    private fun orderByRecent(list:List<City>):List<City>{
        return list.sortedByDescending { it.accessTime }
    }

    fun refreshOnCityDetails(city:City){
        city.updateAccessTime()
        val data = _data.value

        if(data != null) {
            _data.value = orderByRecent(data)
        }
    }

    override fun onCleared() {
        subject.onComplete()
        compositeDisposable.clear()
        super.onCleared()
    }
}