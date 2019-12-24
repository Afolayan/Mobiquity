package com.oluwafemi.mobcategories.data.remote

import com.oluwafemi.mobcategories.ui.product.model.ProductTypeAndData

sealed class DataState {

    object Loading : DataState()
    data class Success(val categories: MutableList<ProductTypeAndData>) : DataState()
    data class Error(val message: String) : DataState()
}
