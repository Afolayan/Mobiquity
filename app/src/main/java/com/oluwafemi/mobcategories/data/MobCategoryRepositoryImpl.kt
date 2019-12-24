package com.oluwafemi.mobcategories.data

import com.oluwafemi.mobcategories.data.remote.MobCategoryService
import com.oluwafemi.mobcategories.data.remote.model.Category
import io.reactivex.Observable
import javax.inject.Inject

class MobCategoryRepositoryImpl @Inject constructor(
    private val categoryService: MobCategoryService) : MobCategoryRepository {

    override fun retrieveCategories(): Observable<MutableList<Category>> {
        return categoryService.fetchCategories()
    }
}
