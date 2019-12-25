package com.weatherapp.di

import com.weatherapp.data.di.RepoModule
import com.weatherapp.data.repo.remote.di.NetworkModule
import com.weatherapp.data.repo.remote.di.RemoteModule
import com.weatherapp.domain.di.UseCaseModule
import com.weatherapp.util.scheduler.SchedulerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class,NetworkModule::class, RepoModule::class,
    UseCaseModule::class, SchedulerModule::class])
interface AppComponent{
    @Component.Builder
    interface Builder {
        fun create(): AppComponent
    }
}