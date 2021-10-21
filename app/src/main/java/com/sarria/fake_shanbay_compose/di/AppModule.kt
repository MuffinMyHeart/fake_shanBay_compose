package com.sarria.fake_shanbay_compose.di

import android.app.Application
import coil.ImageLoader
import com.sarria.fake_shanbay_compose.data.RecommendRepository
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi
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
    fun providesShanBayApi(): ShanBayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ShanBayApi.BASE_URl)
            .build()
            .create(ShanBayApi::class.java)
    }

    @Provides
    @Singleton
    fun providesCoil(applicationContext: Application): ImageLoader =
        ImageLoader.Builder(applicationContext).build()

    @Provides
    @Singleton
    fun providesRecommendRepository(
        shanBayApi: ShanBayApi,
        imageLoader: ImageLoader,
        applicationContext: Application
    ): RecommendRepository =
        RecommendRepository(shanBayApi, imageLoader, applicationContext)
}