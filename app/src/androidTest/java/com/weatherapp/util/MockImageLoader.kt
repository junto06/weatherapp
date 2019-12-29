package com.weatherapp.util

import android.widget.ImageView
import com.weatherapp.R
import com.weatherapp.util.android.imageloading.ImageLoader
import dagger.Module
import javax.inject.Inject

class MockImageLoader @Inject constructor(): ImageLoader {
    override fun loadImage(imageView: ImageView, url: String) {
        val mockImage = R.drawable.abc_ic_clear_material
        imageView.setImageResource(mockImage)
    }
}