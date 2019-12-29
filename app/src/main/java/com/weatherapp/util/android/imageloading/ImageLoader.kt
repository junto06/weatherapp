package com.weatherapp.util.android.imageloading

import android.widget.ImageView

interface ImageLoader{
    fun loadImage(imageView: ImageView, url:String)
}