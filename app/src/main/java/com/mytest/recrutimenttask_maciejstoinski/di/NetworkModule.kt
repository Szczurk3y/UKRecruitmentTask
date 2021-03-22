package com.mytest.recrutimenttask_maciejstoinski.di

import com.mytest.recrutimenttask_maciejstoinski.data.adapters.WeatherAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(WeatherAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()

}