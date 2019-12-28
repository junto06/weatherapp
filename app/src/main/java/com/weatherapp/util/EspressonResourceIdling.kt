package com.weatherapp.util

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/*
EspressonResourceIdling is used for espresso tests to wait for the event loading.

 */

const val RESOURCE_NAME = "espresso_resource_idling"

object EspressonResourceIdling: IdlingResource {

    private val isIdle = AtomicBoolean(true)

    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String  = RESOURCE_NAME

    override fun isIdleNow(): Boolean = isIdle.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    fun idle(state:Boolean){
        isIdle.set(state)
        if(isIdle.get()){
            resourceCallback?.onTransitionToIdle()
        }
    }

}