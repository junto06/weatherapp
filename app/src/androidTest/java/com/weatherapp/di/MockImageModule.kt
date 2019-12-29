package com.weatherapp.di

import com.weatherapp.util.MockImageLoader
import com.weatherapp.util.android.imageloading.ImageLoader
import dagger.Binds
import dagger.Module

@Module
abstract class MockImageModule{
    @Binds
    abstract fun bindImaageLoader(loader:MockImageLoader):ImageLoader
}