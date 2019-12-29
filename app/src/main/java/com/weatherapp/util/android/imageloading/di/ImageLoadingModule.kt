package com.weatherapp.util.android.imageloading.di

import com.weatherapp.util.android.imageloading.ImageLoader
import com.weatherapp.util.android.imageloading.fresco.FrescoLoader
import dagger.Binds
import dagger.Module

@Module
abstract class ImageLoadingModule{
    @Binds
    abstract fun bindWithFresco(imageLoader: FrescoLoader):ImageLoader
}