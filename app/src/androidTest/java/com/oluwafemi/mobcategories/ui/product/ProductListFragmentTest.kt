package com.oluwafemi.mobcategories.ui.product

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oluwafemi.mobcategories.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ProductListFragmentTest {

    @Test
    fun testProductListFragmentHasRecyclerview() {
        launchFragmentInContainer<ProductListFragment>()
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }
}