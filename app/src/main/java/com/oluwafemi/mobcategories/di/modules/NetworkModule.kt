package com.oluwafemi.mobcategories.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.oluwafemi.mobcategories.BuildConfig.BASE_URL
import com.oluwafemi.mobcategories.data.MobCategoryRepository
import com.oluwafemi.mobcategories.data.MobCategoryRepositoryImpl
import com.oluwafemi.mobcategories.data.remote.MobCategoryService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    fun providesCategoryRepository(categoryService: MobCategoryService): MobCategoryRepository {
        return MobCategoryRepositoryImpl(categoryService)
    }

    @Provides
    @Reusable
    @JvmStatic
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @Reusable
    @JvmStatic
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun providesCategoryService(retrofit: Retrofit): MobCategoryService {
        return retrofit.create(MobCategoryService::class.java)
    }
}
