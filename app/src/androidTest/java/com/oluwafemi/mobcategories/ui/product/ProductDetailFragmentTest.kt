package com.oluwafemi.mobcategories.ui.product

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.oluwafemi.mobcategories.R
import com.oluwafemi.mobcategories.data.remote.model.Price
import com.oluwafemi.mobcategories.data.remote.model.Product
import com.oluwafemi.mobcategories.util.Constants
import com.oluwafemi.mobcategories.util.StringUtil
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailFragmentTest {

    private val salesPrice = Price("100", "EUR")
    private val product1 = Product("01", "1", "chocolate", "/chocolate.jpg", "", salesPrice)

    @Test
    fun testProductDetailFragment() {
        val fragmentArgs = Bundle().apply {
            putParcelable(Constants.DATA_KEY, product1)
        }

        launchFragmentInContainer<ProductDetailFragment>(fragmentArgs = fragmentArgs)
        val amount = StringUtil.joinTwoStrings(product1.salePrice.amount, product1.salePrice.currency)

        onView(withId(R.id.iv_product_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_product_name)).check(matches(withText(product1.name)))
        onView(withId(R.id.tv_product_price)).check(matches(withText(amount)))
    }
}