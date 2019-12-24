package com.oluwafemi.mobcategories.ui.product.model

import com.oluwafemi.mobcategories.data.remote.model.Category
import com.oluwafemi.mobcategories.ui.product.enums.CategoryType

object CategoryDataHelper {

    fun getProductTypeDataList(categories: MutableList<Category>,
                               productTypeDataList: MutableList<ProductTypeAndData>): MutableList<ProductTypeAndData> {
        return productTypeDataList.apply {
            clear()
            categories.forEach {
                add(ProductTypeAndData(CategoryType.CATEGORY, categoryName =  it.name))
                addAll(it.products.map {product ->
                    ProductTypeAndData(CategoryType.PRODUCT, product = product)
                }.toMutableList())
            }

        }
    }
}