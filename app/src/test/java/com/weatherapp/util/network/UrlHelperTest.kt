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
}