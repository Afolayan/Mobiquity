package com.oluwafemi.mobcategories.di.modules

import com.oluwafemi.mobcategories.ui.product.ProductDetailFragment
import com.oluwafemi.mobcategories.ui.product.ProductListFragment
import com.oluwafemi.mobcategories.ui.shared.MobCategoryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeProductListFragment(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun contributeProductDetailFragment(): ProductDetailFragment
}
