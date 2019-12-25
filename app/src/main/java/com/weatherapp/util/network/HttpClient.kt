package com.weatherapp.util.network

import okhttp3.OkHttpClient

interface HttpClient{
    fun createHttpClient(): OkHttpClient
}