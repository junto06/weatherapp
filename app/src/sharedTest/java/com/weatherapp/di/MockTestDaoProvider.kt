package com.weatherapp.di

import com.weatherapp.data.storage.CityDao
import dagger.Binds
import dagger.Module

@Module
abstract class MockTestDaoProvider{
    @Binds
    abstract fun bindCityDao(dao:MockCityDao):CityDao
}