package com.weatherapp.util

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

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

fun validateDrawable():TypeSafeMatcher<View>{
    return object :TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {

        }

        override fun matchesSafely(item: View?): Boolean {
            if (item !is ImageView) {
                return false
            }

            val drawable = item.drawable

            return drawable != null
        }
    }
}