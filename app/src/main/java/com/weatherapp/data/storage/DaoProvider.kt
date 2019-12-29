package com.weatherapp.data.storage

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class DaoProvider{
    @Provides
    fun provideCityDao(context: Application):CityDao{
        val db = CityDataBase.getDB(context.applicationContext)

        return db.cityDao()
    }
}