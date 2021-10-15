package com.sarria.fake_shanbay_compose.data.model

data class Article(
    val imageUrl: String,
    val totalReads: Float,
    val type: String,
    val englishTitle: String,
    val chineseIntroduction: String,
    val level: String,
    val totalWords: Int,
    val totalComments: Int,
    val difficulty: String
)