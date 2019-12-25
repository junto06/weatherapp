package com.weatherapp.data.di

import com.weatherapp.data.repo.CityDataSource
import com.weatherapp.data.repo.remote.RemoteCityDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule{
    @Binds
    abstract fun bindDataSource(source:RemoteCityDataSource):CityDataSource
}