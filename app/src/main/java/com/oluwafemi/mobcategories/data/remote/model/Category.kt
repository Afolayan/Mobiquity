package com.oluwafemi.mobcategories.data.remote.model

data class Category(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val products: MutableList<Product> = mutableListOf()
)