package com.sarria.fake_shanbay_compose.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ShanBayApi {
    @GET("/articles/{userId}")
    suspend fun getArticles(@Path("userId") userId: String = "wangqi"): List<Article>

    companion object{
        val BASE_URl = "http://192.168.131.39:8080"
    }
}