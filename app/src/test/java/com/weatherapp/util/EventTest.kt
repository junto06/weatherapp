package com.weatherapp.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EventTest {

    @Test
    fun getEventData_validateContent() {
        //subject under test
        var event = Event("dummy data")

        //get event data
        var data = event.getEventData()

        //shouldn't be null as not consumed event
        assertThat(data).isNotNull()

        assertThat(data).isEqualTo("dummy data")

        //should be consumed now so should be null
        data = event.getEventData()

        assertThat(data).isNull()

        //should null no matter how many time you call
        data = event.getEventData()

        assertThat(data).isNull()
    }
}