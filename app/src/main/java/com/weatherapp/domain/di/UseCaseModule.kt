package com.weatherapp.domain.di

import com.weatherapp.domain.usecase.SearchRepo
import com.weatherapp.domain.usecase.SearchRepoImp
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule{
    @Binds
    abstract fun bind(usecase:SearchRepoImp):SearchRepo
}