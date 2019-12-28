package com.weatherapp.domain.di

import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.domain.usecase.SearchRepoImp
import com.weatherapp.domain.usecase.WeatherRepo
import com.weatherapp.domain.usecase.WeatherRepoImp
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule{
    @Binds
    abstract fun bind(usecase:SearchRepoImp):SearchRepo

    @Binds
    abstract fun bindWeatherInfo(usecase:WeatherRepoImp):WeatherRepo
}