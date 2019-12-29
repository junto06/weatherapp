package com.weatherapp.util.android.imageloading.fresco

import android.net.Uri
import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import com.weatherapp.util.android.imageloading.ImageLoader
import com.weatherapp.util.network.UrlHelper
import javax.inject.Inject

class FrescoLoader @Inject constructor(): ImageLoader {
    override fun loadImage(imageView: ImageView, url: String) {
        var httpUrl = UrlHelper.validateHttps(url)
        if(!UrlHelper.isValidUrl(httpUrl)){
            //Do no load if not a valid url.
            return
        }
        val simpleDraweeView = requireNotNull(imageView as SimpleDraweeView)
        simpleDraweeView.setImageURI(Uri.parse(httpUrl),null)
    }
}