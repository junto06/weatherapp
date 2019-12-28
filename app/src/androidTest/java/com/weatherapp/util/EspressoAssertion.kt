package com.weatherapp.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun viewMatcherRecyclerView(block:(RecyclerView?) -> Boolean): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java){
        override fun describeTo(description: Description?) {
            description?.appendText("viewMatcherRecyclerView with lambda")
        }
        override fun matchesSafely(item: RecyclerView?): Boolean {
            return block(item)
        }
    }
}