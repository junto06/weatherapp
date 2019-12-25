package com.weatherapp.util.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper:RetrofitClient{
    override fun createRetrofit(config: HttpConfig, client: HttpClient):
            Retrofit? {
        if(!UrlHelper.isValidUrl(config.baseUrl())){
            return null
        }
        return Retrofit.Builder()
            .client(client.createHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(config.baseUrl())
            .build()
    }
}