package com.weatherapp.data.storage

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class DaoProvider{
    @Provides
    fun provideCityDao(context: Application):CityDao{
        val db = CityDataBase.getDB(context.applicationContext)

        return db.cityDao()
    }
}