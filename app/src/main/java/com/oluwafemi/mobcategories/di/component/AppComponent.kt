package com.oluwafemi.mobcategories.di.component

import android.app.Application
import com.oluwafemi.mobcategories.MobCategoryApplication
import com.oluwafemi.mobcategories.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
                      AppModule::class,
                      ActivityModule::class,
                      FragmentModule::class,
                      NetworkModule::class,
                      ViewModelModule::class])
interface AppComponent : AndroidInjector<MobCategoryApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
