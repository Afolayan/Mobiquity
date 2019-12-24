package com.oluwafemi.mobcategories.di.modules

import com.oluwafemi.mobcategories.ui.shared.MobCategoryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMobCategoryActivity(): MobCategoryActivity
}
