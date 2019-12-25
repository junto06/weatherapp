package com.weatherapp.di

import com.weatherapp.data.repo.remote.di.RemoteModule
import com.weatherapp.data.repo.remote.mapper.CityMapperImpTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class])
interface TestComponent:AppComponent{
    fun inject(target: CityMapperImpTest)
}