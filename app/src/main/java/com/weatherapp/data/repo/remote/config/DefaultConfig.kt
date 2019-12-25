package com.weatherapp.data.repo.remote.config

import com.weatherapp.BuildConfig
import com.weatherapp.util.network.HttpConfig

object DefaultConfig{

    fun config():HttpConfig{
        return if(BuildConfig.DEBUG){
            DevConfig()
        }else {
            ProdConfig()
        }
    }
}