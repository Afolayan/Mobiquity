package com.oluwafemi.mobcategories.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oluwafemi.mobcategories.R
import com.oluwafemi.mobcategories.data.remote.model.Product
import com.oluwafemi.mobcategories.ui.shared.PagesFragment
import com.oluwafemi.mobcategories.util.Constants
import com.oluwafemi.mobcategories.util.ImageLoader
import com.oluwafemi.mobcategories.util.StringUtil
import kotlinx.android.synthetic.main.product_details.*
import javax.inject.Inject

class ProductDetailFragment: PagesFragment() {

    @Inject lateinit var imageLoader: ImageLoader

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.product_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = arguments?.getParcelable<Product>(Constants.DATA_KEY)
        setupViews(product)
    }

    private fun setupViews(product: Product?) {
        product?.let {
            imageLoader.loadImage(it.url, iv_product_image)
            tv_product_name.text = it.name
            tv_product_price.text =
                StringUtil.joinTwoStrings(it.salePrice.amount, it.salePrice.currency)
        }
    }
}