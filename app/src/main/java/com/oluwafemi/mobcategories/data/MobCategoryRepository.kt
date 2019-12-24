package com.oluwafemi.mobcategories.data

import com.oluwafemi.mobcategories.data.remote.model.Category
import io.reactivex.Observable

interface MobCategoryRepository {

    fun retrieveCategories(): Observable<MutableList<Category>>
}
