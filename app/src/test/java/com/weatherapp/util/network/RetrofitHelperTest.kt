package com.weatherapp.util.network

import com.google.common.truth.Truth.assertThat
import com.weatherapp.util.network.HttpConfig
import com.weatherapp.util.network.RetrofitHelper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

class RetrofitHelperTest {

    @Mock
    lateinit var httpConfig: HttpConfig

    lateinit var httpClient:HttpClient

    @Before
    fun testSetup(){
        MockitoAnnotations.initMocks(this)
        httpClient = OkHttpHelper
    }

    @Test
    fun createRetrofit_invalidBaseUrl_ShouldReturnNull() {
        //setup invalid mock url
        val baseUrl = "test/"

        `when`(httpConfig.baseUrl()).thenReturn(baseUrl)

        //create retrofit client
        val client = RetrofitHelper.createRetrofit(httpConfig,httpClient)

        //should be null as we are passing invalid url
        assertThat(client).isNull()
    }

    @Test
    fun createRetrofit_validBaseUrl_ShouldReturnRetrofitClient() {
        //setup valid mock url
        val baseUrl = "http://test.com/"

        `when`(httpConfig.baseUrl()).thenReturn(baseUrl)

        //create retrofit client
        val client = RetrofitHelper.createRetrofit(httpConfig,httpClient)

        //should not be null as we are passing valid url
        assertThat(client).isNotNull()

        //should be instance of Retrofit client
        assertThat(client is Retrofit).isTrue()
    }
}