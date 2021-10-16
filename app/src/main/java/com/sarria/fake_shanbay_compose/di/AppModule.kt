package com.sarria.fake_shanbay_compose.di

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesShanBayApi(): ShanBayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ShanBayApi.BASE_URl)
            .build()
            .create(ShanBayApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoil(@ApplicationContext applicationContext: Application): ImageLoader =
        ImageLoader.Builder(applicationContext).build()
}