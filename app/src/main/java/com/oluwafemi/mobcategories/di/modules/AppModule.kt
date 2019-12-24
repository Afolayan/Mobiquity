package com.oluwafemi.mobcategories.di.modules

import com.oluwafemi.mobcategories.util.AppScheduler
import com.oluwafemi.mobcategories.util.GlideImageLoader
import com.oluwafemi.mobcategories.util.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object AppModule {

    @Provides
    @Reusable
    @JvmStatic
    fun providesAppScheduler(): AppScheduler = AppScheduler()

    @Provides
    @Reusable
    @JvmStatic
    fun providesImageLoader(): ImageLoader = GlideImageLoader()

}
