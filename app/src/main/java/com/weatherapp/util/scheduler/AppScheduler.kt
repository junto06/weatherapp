package com.weatherapp.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppScheduler @Inject constructor():IScheduler{
    override fun io(): Scheduler = Schedulers.io()

    override fun main(): Scheduler  = AndroidSchedulers.mainThread()
}