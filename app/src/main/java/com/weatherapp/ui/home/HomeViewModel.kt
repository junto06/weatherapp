package com.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weatherapp.R
import com.weatherapp.data.model.City
import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.util.Event
import com.weatherapp.util.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val searchRepo: SearchRepo,
                    private val scheduler:IScheduler):ViewModel(){

    private val subject:PublishSubject<String> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()

    private val _data = MutableLiveData<List<City>>()
    val data:MutableLiveData<List<City>> = _data

    //error indicating error state
    private val _error = MutableLiveData<Event<Any>>()
    val error:MutableLiveData<Event<Any>> = _error

    init {
        setupSearch()
    }

    fun search(query:String){
        subject.onNext(query)
    }

    private fun setupSearch(){
        val disposable = subject.debounce(400,TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .switchMap {
                searchRepo.searchCity(it)
                    .doOnError {th ->
                        th.printStackTrace()
                        _error.value = Event(R.string.error_loading_data)
                    }
            }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.main())
            .subscribe ({

                if(it.hasError){
                    _error.value = Event(it.errorMessage)
                }else{
                    _data.value = it.cityList
                }

                println(it.toString())
            },{

                it.printStackTrace()
                _error.value = Event(R.string.error_loading_data)

            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        subject.onComplete()
        compositeDisposable.clear()
        super.onCleared()
    }
}