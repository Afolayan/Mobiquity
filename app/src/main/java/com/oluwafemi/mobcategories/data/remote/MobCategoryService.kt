package com.oluwafemi.mobcategories.data.remote

import com.oluwafemi.mobcategories.data.remote.model.Category
import io.reactivex.Observable
import retrofit2.http.GET

interface MobCategoryService {

    @GET("/")
    fun fetchCategories(): Observable<MutableList<Category>>
}
