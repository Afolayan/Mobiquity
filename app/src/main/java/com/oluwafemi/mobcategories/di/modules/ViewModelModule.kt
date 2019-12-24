package com.oluwafemi.mobcategories.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oluwafemi.mobcategories.di.ViewModelFactory
import com.oluwafemi.mobcategories.di.ViewModelKey
import com.oluwafemi.mobcategories.ui.product.vm.ProductVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProductVM::class)
    internal abstract fun productViewModel(viewModel: ProductVM): ViewModel
}
