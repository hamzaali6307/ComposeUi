package com.hamy.composeuilearning.ui.network.di

import com.hamy.composeuilearning.ui.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi
        .Builder().add(KotlinJsonAdapterFactory()).build()


    @Provides
    fun provideApiService(moshi: Moshi): ApiService =
        Retrofit.Builder().run {
            baseUrl(ApiService.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
        }.create(ApiService::class.java)

}