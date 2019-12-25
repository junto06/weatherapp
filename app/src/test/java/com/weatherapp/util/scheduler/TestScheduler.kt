package com.weatherapp.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TestScheduler @Inject constructor():IScheduler{
    override fun io(): Scheduler  = Schedulers.trampoline()

    override fun main(): Scheduler = Schedulers.trampoline()

}