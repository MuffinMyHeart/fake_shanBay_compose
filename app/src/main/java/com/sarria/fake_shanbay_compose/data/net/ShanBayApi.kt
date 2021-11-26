package com.sarria.fake_shanbay_compose.data.net

import com.sarria.fake_shanbay_compose.data.model.Article
import com.sarria.fake_shanbay_compose.data.model.ClockOnCardInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShanBayApi {
    @GET("/recommend/articles/{userId}")
    suspend fun getArticles(
        @Path("userId") userId: String,
        @Query("nums") nums: Int = 5
    ): List<Article>

    @GET("/recommend/clockOnCardInfo/{userId}")
    suspend fun getClockOnCardInfo(@Path("userId") userId: String): ClockOnCardInfo

    @GET("/recommend/todayPushMessage")
    suspend fun getTodayPushMessage(): List<String>

    companion object {
        const val BASE_URl = "http://sarria.cn"
    }
}