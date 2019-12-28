package com.weatherapp.data.repo.remote.di

import com.weatherapp.data.repo.remote.config.DefaultConfig
import com.weatherapp.data.repo.remote.mapper.CityMapper
import com.weatherapp.data.repo.remote.mapper.CityMapperImp
import com.weatherapp.data.repo.remote.mapper.WeatherMapper
import com.weatherapp.data.repo.remote.mapper.WeatherMapperImp
import com.weatherapp.util.network.HttpConfig
import dagger.Module
import dagger.Provides

@Module
class RemoteModule{
    @Provides
    fun mapper(): CityMapper = CityMapperImp()

    @Provides
    fun weatherMapper(): WeatherMapper = WeatherMapperImp()

    @Provides
    fun httpConfig():HttpConfig = DefaultConfig.config()
}