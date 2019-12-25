package com.weatherapp.data.repo.remote.config

import com.weatherapp.util.network.HttpConfig

class DevConfig:HttpConfig{
    override fun baseUrl(): String  = "https://api.worldweatheronline.com/premium/v1/"

    override fun apiKey(): String = "725d29dd08654748aea133036192212"

}