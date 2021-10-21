package com.sarria.fake_shanbay_compose.data

import android.app.Application
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import com.sarria.fake_shanbay_compose.data.model.Article
import com.sarria.fake_shanbay_compose.data.model.ClockOnCardInfo
import com.sarria.fake_shanbay_compose.data.net.ShanBayApi
import kotlinx.coroutines.flow.*

class RecommendRepository(
    private val shanBayApi: ShanBayApi,
    private val imageLoader: ImageLoader,
    private val applicationContext: Application
) {

    fun getArticles(userId: String): Flow<Article> = flow {
        val articles = shanBayApi.getArticles(userId = userId, nums = 20)
        articles.forEach {
            emit(it)
        }
    }.flatMapMerge {
        flow {
            it.imageBitmap = getImage(it.imageUrl)
            emit(it)
        }
    }.filter { it.imageBitmap != null }

    fun getClockOnCardInfo(userId: String): Flow<ClockOnCardInfo> = flow {
        val clockOnCardInfo = shanBayApi.getClockOnCardInfo(userId = userId)
        val urls = clockOnCardInfo.headerUrls.asFlow()
            .flatMapMerge {
                flow {
                    emit(getImage(it))
                }
            }.filter { it != null }.toList()
        clockOnCardInfo.imageBitmaps = urls
        emit(clockOnCardInfo)
    }

    fun getTodayPushMessage(): Flow<List<String>> = flow {
        val todayMessage = shanBayApi.getTodayPushMessage()
        emit(todayMessage)
    }

    private suspend fun getImage(url: String): ImageBitmap? {
        val imageRequest = ImageRequest.Builder(applicationContext)
            .data(url)
            .build()
        return imageLoader.execute(request = imageRequest).drawable?.toBitmap()?.asImageBitmap()
    }
}