package com.weatherapp.data.storage

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weatherapp.data.model.City

const val DB_NAME = "weather_db"

@Database(entities = [City::class],version = 1)
abstract class CityDataBase: RoomDatabase() {

    abstract fun cityDao():CityDao

    companion object{
        fun getDB(context: Context):CityDataBase{
            return Room.databaseBuilder(context,CityDataBase::class.java,DB_NAME).allowMainThreadQueries().build()
        }

        @VisibleForTesting
        fun getTestDB(context: Context):CityDataBase{
            return Room.inMemoryDatabaseBuilder(context,CityDataBase::class.java).allowMainThreadQueries().build()
        }
    }

}