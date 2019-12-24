package com.oluwafemi.mobcategories

import android.app.Activity
import com.oluwafemi.mobcategories.di.component.DaggerAppComponent
import dagger.android.*
import javax.inject.Inject

class MobCategoryApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private val appComponent: AndroidInjector<MobCategoryApplication> by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
