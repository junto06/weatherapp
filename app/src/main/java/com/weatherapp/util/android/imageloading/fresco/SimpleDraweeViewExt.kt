package com.weatherapp.util.android.imageloading.fresco

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.weatherapp.util.android.imageloading.ImageLoader

@BindingAdapter(value = ["url","imageLoader"])
fun SimpleDraweeView.loadThumbnail(url:String?, imageLoader: ImageLoader){
    if(url != null) {
        imageLoader.loadImage(this, url)
    }
}