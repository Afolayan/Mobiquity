package com.oluwafemi.mobcategories.ui.product.model

import com.oluwafemi.mobcategories.data.remote.model.Product
import com.oluwafemi.mobcategories.ui.product.enums.CategoryType

data class ProductTypeAndData(
    val type: CategoryType,
    val product: Product? = null,
    val categoryName: String? = null
)