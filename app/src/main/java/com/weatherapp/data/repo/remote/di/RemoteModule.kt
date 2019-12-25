package com.weatherapp.data.repo.remote.di

import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.data.repo.remote.mapper.CityMapperImp
import dagger.Binds
import dagger.Module

@Module
abstract class RemoteModule{
    @Binds
    abstract fun mapper(mapper:CityMapperImp): CityMapper
}