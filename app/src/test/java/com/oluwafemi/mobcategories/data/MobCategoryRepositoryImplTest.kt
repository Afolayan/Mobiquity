package com.oluwafemi.mobcategories.data

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.oluwafemi.mobcategories.Mocks
import com.oluwafemi.mobcategories.data.remote.MobCategoryService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

class MobCategoryRepositoryImplTest {

    @Mock lateinit var categoryService: MobCategoryService
    lateinit var categoryRepositoryImpl: MobCategoryRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        categoryRepositoryImpl = MobCategoryRepositoryImpl(categoryService)
    }


    @Test
    fun `retrieveCategories returns Category values when request is successful`() {
        whenever(categoryService.fetchCategories()).thenReturn(
            Observable.just(Mocks.createCategories())
        )
        categoryRepositoryImpl.retrieveCategories()
            .test()
            .assertValue(Mocks.createCategories())
            .dispose()
        verify(categoryService).fetchCategories()
    }

    @Test
    fun `retrieveCategories does not return any value when request has exception`() {
        whenever(categoryService.fetchCategories()).thenReturn(
            Observable.error(IOException())
        )
        categoryRepositoryImpl.retrieveCategories()
            .test()
            .assertNoValues()
            .dispose()
        verify(categoryService).fetchCategories()
    }
}