package com.weatherapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "city",primaryKeys = ["name","country","region"])
@Parcelize
data class City(val name:String,val country:String,val region:String,
                val latitude:Double,val longitude:Double,
                var accessTime:Long = System.currentTimeMillis()) : Parcelable{

    fun updateAccessTime(){
        accessTime = System.currentTimeMillis()
    }
}