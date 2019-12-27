package com.oluwafemi.mobcategories.ui.product.model

import com.oluwafemi.mobcategories.Mocks
import com.oluwafemi.mobcategories.ui.product.enums.CategoryType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryDataHelperTest {

    @Test
    fun `getProductTypeDataList returns non-empty list` () {
        val categories = Mocks.createCategories()
        val itemList = mutableListOf<ProductTypeAndData>()

        assert(itemList.isEmpty())

        val result = CategoryDataHelper.getProductTypeDataList(categories, itemList)

        assertNotNull(result)
        assert(itemList.isNotEmpty())
    }
    
    @Test
    fun `getProductTypeDataList returns list of productTypeData from Categories` () {
        val categories = Mocks.createCategories()
        val itemList = mutableListOf<ProductTypeAndData>()

        val result = CategoryDataHelper.getProductTypeDataList(categories, itemList)

        assertNotNull(result)
        assertEquals(result, itemList)
    }

    @Test
    fun `getProductTypeDataList returns list with first item being a category type` () {
        val categories = Mocks.createCategories()
        val itemList = mutableListOf<ProductTypeAndData>()

        val result = CategoryDataHelper.getProductTypeDataList(categories, itemList)

        assertNotNull(result)
        assertEquals(result[0].type, CategoryType.CATEGORY)
    }

}