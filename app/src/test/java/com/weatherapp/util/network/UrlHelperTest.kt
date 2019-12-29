package com.weatherapp.util.network

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class UrlHelperTest {

    @Test
    fun isValidUrl_EmptyUrl_ShouldReturnFalse() {
        //empty url so shouldn't valid
        val baseUrl = ""
        assertThat(UrlHelper.isValidUrl(baseUrl)).isFalse()
    }

    @Test
    fun isValidUrl_InvalidUrl_ShouldReturnFalse() {
        //invalid url. Should start with http protocol
        val baseUrl = "test.com"
        assertThat(UrlHelper.isValidUrl(baseUrl)).isFalse()
    }

    @Test
    fun isValidUrl_validUrl_ShouldReturnTrue() {
        //valid url
        val baseUrl = "http://test.com/"
        assertThat(UrlHelper.isValidUrl(baseUrl)).isTrue()
    }

    @Test
    fun validateHttps_httpUrl_ShouldBeConvertedToHttps() {
        //http url
        val baseUrl = "http://test.com/"
        assertThat(UrlHelper.validateHttps(baseUrl)).isEqualTo("https://test.com/")
    }

    @Test
    fun validateHttps_httpsUrl_ShouldReturnSame() {
        //https url
        val baseUrl = "https://test.com/"
        assertThat(UrlHelper.validateHttps(baseUrl)).isEqualTo("https://test.com/")
    }
}