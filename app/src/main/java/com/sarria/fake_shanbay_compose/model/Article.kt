package com.sarria.fake_shanbay_compose.model

import com.sarria.fake_shanbay_compose.R

data class Article(
    val imageSrc: String,
    val totalReads: Int,
    val type: String,
    val englishTitle: String,
    val chineseIntroduction: String,
    val level: String,
    val totalWords: Int,
    val totalComments: Int,
    val difficulty: String
)

fun getArticle(): Article {
    return Article(
        imageSrc = R.mipmap.image1.toString(),
        totalReads = 256,
        type = "商业",
        englishTitle = "Hello This Is Jetpack Compose",
        chineseIntroduction = "如何学习安卓最新的开发工具包",
        level = "初级",
        totalWords = 400,
        totalComments = 300,
        difficulty = "难度适中"
    )
}
