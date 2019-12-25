package com.weatherapp.util.network

import retrofit2.Retrofit

interface RetrofitClient{

    fun createRetrofit(config: HttpConfig, client: HttpClient):
            Retrofit?
}