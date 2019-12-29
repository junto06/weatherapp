package com.weatherapp.di

import android.app.Application
import com.weatherapp.data.storage.CityDao
import com.weatherapp.data.storage.CityDataBase
import dagger.Module
import dagger.Provides

@Module
class DaoModule{
    @Provides
    fun provideCityDao(context: Application): CityDao {
        val db = CityDataBase.getTestDB(context.applicationContext)

        return db.cityDao()
    }
}