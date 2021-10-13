package com.sarria.fake_shanbay_compose.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ShanBayApi {
    @GET("/articles/{userId}")
    suspend fun getArticles(@Path("userId") userId: String = "wangqi"): List<Article>

    companion object{
        val BASE_URl = "http://101.132.97.199"
//        val BASE_URl = "http://localhost"
    }
}