package com.weatherapp.data.di

import com.weatherapp.data.repo.DataSource
import com.weatherapp.data.repo.remote.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule{
    @Binds
    abstract fun bindDataSource(source:RemoteDataSource):DataSource
}