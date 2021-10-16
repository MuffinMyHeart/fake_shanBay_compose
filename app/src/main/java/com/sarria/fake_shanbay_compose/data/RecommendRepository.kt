package com.sarria.fake_shanbay_compose.data

import android.app.Application
import android.widget.ImageView
import androidx.core.widget.ImageViewCompat
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendRepository @Inject constructor(
    private val shanBayApi: ShanBayApi,
    private val imageLoader: ImageLoader,
    @ApplicationContext private val applicationContext: Application
) {

    fun getArticleByUserId(userId: String) = flow {
        val articles = shanBayApi.getArticles(userId = userId)
        emit(articles)
    }

    fun getImages(urls: List<String>) = urls.asFlow()
        .flatMapMerge { url ->
        flow {
            val imageRequest = ImageRequest.Builder(applicationContext)
                .data(url)
                .build()
            val imageResult = imageLoader.execute(request = imageRequest)
            emit(imageResult)
        }
    }
}