package com.weatherapp.util.scheduler

import io.reactivex.Scheduler

interface IScheduler{
    fun io(): Scheduler

    fun main(): Scheduler
}