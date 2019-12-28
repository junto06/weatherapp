package com.weatherapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(val name:String,val country:String,val region:String,
                val latitude:Double,val longitude:Double) : Parcelable