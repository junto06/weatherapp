package com.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weatherapp.R
import com.weatherapp.data.model.City
import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.util.scheduler.IScheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class HomeViewModel(private val searchRepo: SearchRepo,
                    private val scheduler:IScheduler):ViewModel(){

    private val subject:PublishSubject<String> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()

    private val _data = MutableLiveData<List<City>>()
    val data:MutableLiveData<List<City>> = _data

    //error indicating error state
    private val _error = MutableLiveData<Any>()
    val error:MutableLiveData<Any> = _error

    init {
        setupSearch()
    }

    fun search(query:String){
        subject.onNext(query)
    }

    private fun setupSearch(){
        val disposable = subject.debounce(200,TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                searchRepo.searchCity(it)
                    .doOnError {
                        _error.value = R.string.error_loading_data
                    }
            }
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.main())
            .subscribe {
                if(it.hasError){
                    _error.value = it.errorMessage
                }else{
                    _data.value = it.cityList
                }
            }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        subject.onComplete()
        compositeDisposable.clear()
        super.onCleared()
    }
}