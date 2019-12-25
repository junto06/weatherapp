package com.weatherapp.util.scheduler

import dagger.Binds
import dagger.Module

@Module
abstract class SchedulerModule{
    @Binds
    abstract fun bind(scheduler: AppScheduler):IScheduler
}