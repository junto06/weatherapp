package com.weatherapp.data.storage

import androidx.room.*
import com.weatherapp.data.model.City

@Dao
interface CityDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city:City):Long
}