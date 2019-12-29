package com.weatherapp.data.storage

import androidx.room.*
import com.weatherapp.data.model.City
import io.reactivex.Observable

@Dao
interface CityDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city:City):Long

    @Query(value = "Select * FROM city")
    fun getRecentCities():Observable<List<City>>
}