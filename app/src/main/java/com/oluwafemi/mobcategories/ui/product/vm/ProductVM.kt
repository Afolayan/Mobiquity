package com.oluwafemi.mobcategories.ui.product.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oluwafemi.mobcategories.data.MobCategoryRepository
import com.oluwafemi.mobcategories.data.remote.DataState
import com.oluwafemi.mobcategories.ui.product.model.CategoryDataHelper
import com.oluwafemi.mobcategories.ui.product.model.ProductTypeAndData
import com.oluwafemi.mobcategories.util.AppScheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProductVM @Inject constructor(
    private val appScheduler: AppScheduler,
    private val mobCategoryRepository: MobCategoryRepository): ViewModel() {

    private var _categoryState = MutableLiveData<DataState>()
    private var disposable = CompositeDisposable()
    var itemList = mutableListOf<ProductTypeAndData>()

    val dataState: LiveData<DataState>
        get() = _categoryState

    init {
        _categoryState.value = DataState.Loading
    }

    fun retrieveData() {
        disposable.add(
            mobCategoryRepository.retrieveCategories()
                .observeOn(appScheduler.main())
                .subscribeOn(appScheduler.io())
                .subscribe(
                    { categoryData ->
                        val productList = CategoryDataHelper.getProductTypeDataList(categoryData, itemList)
                        _categoryState.value = DataState.Success(productList)
                    },
                    { error ->
                        _categoryState.value = DataState.Error("Message: ${error.message}")
                    })

        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}