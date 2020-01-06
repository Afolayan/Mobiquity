package com.oluwafemi.mobcategories.ui.product.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.oluwafemi.mobcategories.Mocks
import com.oluwafemi.mobcategories.data.MobCategoryRepository
import com.oluwafemi.mobcategories.data.remote.DataState
import com.oluwafemi.mobcategories.ui.product.model.CategoryDataHelper
import com.oluwafemi.mobcategories.util.AppScheduler
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProductVMTest {

    @get: Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Mock lateinit var scheduler: AppScheduler
    @Mock lateinit var categoryRepository: MobCategoryRepository
    @Mock lateinit var observer: Observer<DataState>
    private lateinit var productVM: ProductVM

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `dataState is not null with a null response`() {

        whenever(scheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(scheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(categoryRepository.retrieveCategories())
            .thenReturn(null)

        productVM = ProductVM(scheduler, categoryRepository)

        assertNotNull(productVM.dataState)
    }

    @Test
    fun `dataState returns loading when initializing viewModel`() {

        whenever(scheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(scheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(categoryRepository.retrieveCategories())
            .thenReturn(Observable.just(Mocks.createCategories()))

        productVM = ProductVM(scheduler, categoryRepository)

        assertEquals(productVM.dataState.value, DataState.Loading)
    }

    @Test
    fun `retrieveData returns error when request fails`() {

        val error = Throwable("Request Timeout")
        whenever(scheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(scheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(categoryRepository.retrieveCategories())
            .thenReturn(Observable.error(error))

        productVM = ProductVM(scheduler, categoryRepository)
        productVM.dataState.observeForever(observer)

        verify(categoryRepository).retrieveCategories()
        verify(observer).onChanged(DataState.Error("Message: ${error.message}"))
    }

    @Test
    fun `retrieveData returns success when request is successful`() {

        whenever(scheduler.main()).thenReturn(Schedulers.trampoline())
        whenever(scheduler.io()).thenReturn(Schedulers.trampoline())
        whenever(categoryRepository.retrieveCategories())
            .thenReturn(Observable.just(Mocks.createCategories()))

        productVM = ProductVM(scheduler, categoryRepository)
        productVM.dataState.observeForever(observer)

        verify(observer).onChanged(DataState.Success(
            CategoryDataHelper.getProductTypeDataList(Mocks.createCategories(), productVM.itemList)))
    }
}