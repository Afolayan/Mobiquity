package com.oluwafemi.mobcategories

import com.oluwafemi.mobcategories.data.remote.model.Category
import com.oluwafemi.mobcategories.data.remote.model.Price
import com.oluwafemi.mobcategories.data.remote.model.Product

object Mocks {

    private fun createSalesPrice(amount: String = "100", currency: String = "EUR"): Price {
        return Price(amount, currency)
    }

    fun createProduct(id: String = "01",
                      categoryId: String = "1",
                      name: String = "chocolate",
                      url: String = "",
                      description: String = "/chocolate.jpg", salesPrice: Price = createSalesPrice()) : Product {
        return Product(id, categoryId, name, url, description, salesPrice)
    }

    fun createCategories(id: String = "1",
                         name: String = "snack",
                         description: String = "",
                         products: MutableList<Product> = mutableListOf(createProduct())): MutableList<Category> {
        return mutableListOf(Category(id, name, description, products))
    }
}
