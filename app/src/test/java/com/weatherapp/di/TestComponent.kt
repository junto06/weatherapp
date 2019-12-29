package com.weatherapp.di

import com.weatherapp.data.di.RepoModule
import com.weatherapp.data.repo.remote.CurrentWeatherRemoteDataSourceTest
import com.weatherapp.data.repo.remote.SearchRemoteCityDataSourceTest
import com.weatherapp.data.repo.remote.di.RemoteModule
import com.weatherapp.domain.usecase.LoadCityWeatherTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RemoteModule::class,MockRemoteModule::class, RepoModule::class,MockTestDaoProvider::class])
interface TestComponent{

    fun inject(target: SearchRemoteCityDataSourceTest)

    fun inject(target: CurrentWeatherRemoteDataSourceTest)

    fun inject(target: LoadCityWeatherTest)
}