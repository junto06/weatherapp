package com.weatherapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.weatherapp.runner.TestApp
import com.weatherapp.ui.MainActivity
import com.weatherapp.ui.home.adapter.HomeAdapter
import com.weatherapp.ui.home.adapter.HomeViewItem
import com.weatherapp.util.EspressonResourceIdling
import com.weatherapp.util.viewMatcherRecyclerView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/*
    These are end to end tests
 */

@MediumTest
@RunWith(AndroidJUnit4::class)
class AppTests{

    @Before
    fun register(){
        IdlingRegistry.getInstance().register(EspressonResourceIdling)
    }

    fun unregister(){
        IdlingRegistry.getInstance().unregister(EspressonResourceIdling)
    }

    @Test
    fun testApplication() {

        //start activity
        ActivityScenario.launch(MainActivity::class.java)

        //check if main Navigation Fragment is visible
        onView(withId(R.id.mainNavFragment)).check(matches(isDisplayed()))

        //check if HomeFragment is visible
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()))

        //check if city List View is visible
        onView(withId(R.id.cityList)).check(matches(isDisplayed()))

        //check if city List adapter is instance of HomeAdapter
        onView(withId(R.id.cityList))
            .check(matches(viewMatcherRecyclerView{ it?.adapter is HomeAdapter }))

        //check if city List count is zero initially
        onView(withId(R.id.cityList))
            .check(matches(viewMatcherRecyclerView{ it?.adapter?.itemCount!! == 0 }))

        //perform search
        onView(withId(R.id.action_search)).perform(click())

        //type query
        onView(withId(R.id.search_src_text))
            .perform(typeText("New York"))

        //close keyboard
        Espresso.closeSoftKeyboard()

        //verify items in cityList. Should hae only 1 item

        onView(withId(R.id.cityList))
            .check(matches(viewMatcherRecyclerView{ it?.adapter?.itemCount!! == 1 }))

        //perform search again.
        onView(withId(R.id.action_search)).perform(click())

        //clear existing text
        onView(withId(R.id.search_src_text))
            .perform(clearText())

        //type query. Search random string
        onView(withId(R.id.search_src_text))
            .perform(typeText("xyz"))

        //close keyboard
        Espresso.closeSoftKeyboard()

        //verify items in cityList. Should hae only 1 item

        onView(withId(R.id.cityList))
            .check(matches(viewMatcherRecyclerView{ it?.adapter?.itemCount!! == 0 }))


        //open details page

        //clear existing text
        onView(withId(R.id.search_src_text))
            .perform(clearText())

        //first load some data. Type query
        onView(withId(R.id.search_src_text))
            .perform(typeText("New York"))

        //close keyboard
        Espresso.closeSoftKeyboard()

        //verify items in cityList. Should hae only 1 item

        onView(withId(R.id.cityList))
            .check(matches(viewMatcherRecyclerView{ it?.adapter?.itemCount!! == 1 }))

        //open details page
        onView(withId(R.id.cityList)).perform(actionOnItemAtPosition<HomeViewItem>(0, click()))

    }
}