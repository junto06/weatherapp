package com.weatherapp.util.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object OkHttpHelper:HttpClient{

    override fun createHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                println("Network-: $message")
            }
        })
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}