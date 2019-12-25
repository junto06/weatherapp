package com.weatherapp.di

import com.weatherapp.data.repo.remote.di.NetworkModule
import com.weatherapp.data.repo.remote.di.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class,NetworkModule::class])
interface AppComponent{
    @Component.Builder
    interface Builder {
        fun create(): AppComponent
    }
}