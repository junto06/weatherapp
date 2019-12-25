package com.weatherapp.data.repo.remote.config

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class DefaultConfigTest {

    @Test
    fun config_defaultConfiguration_ShouldBeValid() {
        val config = DefaultConfig.config()

        assertThat(config.baseUrl()).isNotEmpty()

        assertThat(config.apiKey()).isNotEmpty()

        assertThat(config.format()).isNotEmpty()
    }
}