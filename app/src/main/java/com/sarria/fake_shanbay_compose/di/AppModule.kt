package com.sarria.fake_shanbay_compose.di

import com.sarria.fake_shanbay_compose.data.ShanBayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideShanBayApi(): ShanBayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ShanBayApi.BASE_URl)
            .build()
            .create(ShanBayApi::class.java)
    }
}