package com.weatherapp.util.network

import okhttp3.OkHttpClient


object OkHttpHelper:HttpClient{

    override fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}