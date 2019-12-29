package com.weatherapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.weatherapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class App:Application(), HasAndroidInjector {
    companion object{
        lateinit var instance:App
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        instance = this

        Fresco.initialize(this)

        buildAndInject()
    }

    open fun buildAndInject(){
        DaggerAppComponent
            .builder()
            .application(this)
            .create()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}