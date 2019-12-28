package com.weatherapp.di

import com.weatherapp.data.repo.remote.CurrentWeatherRemoteDataSourceTest
import com.weatherapp.data.repo.remote.SearchRemoteCityDataSourceTest
import com.weatherapp.data.repo.remote.di.RemoteModule
import com.weatherapp.data.repo.remote.mapper.CityMapperImpTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class,MockRemoteModule::class])
interface TestComponent{

    fun inject(target: SearchRemoteCityDataSourceTest)

    fun inject(target: CurrentWeatherRemoteDataSourceTest)
}