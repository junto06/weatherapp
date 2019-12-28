package com.weatherapp.runner

import com.weatherapp.App
import com.weatherapp.data.repo.remote.dto.CityInfo
import com.weatherapp.di.DaggerTestAppComponent
import com.weatherapp.di.MockRemoteModule
import com.weatherapp.mockfactory.CityMockFactory
import com.weatherapp.mockfactory.MockResponseFactory

class TestApp: App(){
    override fun buildAndInject() {

        //list of mock cities
        val cities = mockTestData()

        //mock search response
        val searchResponse = MockResponseFactory.searchResponse(cities)

        //setup dagger
        DaggerTestAppComponent
            .builder()
            .mockRemoteModule(MockRemoteModule(searchResponse))
            .application(this)
            .create()
            .inject(this)
    }

    fun mockTestData():List<CityInfo>{
        return CityMockFactory.generateCities(listOf(
            "New York","New Hampshire, New Orleans"
        ),"United states")
    }
}