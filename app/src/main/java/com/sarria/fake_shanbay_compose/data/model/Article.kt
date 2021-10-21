package com.sarria.fake_shanbay_compose.data.model

import androidx.compose.ui.graphics.ImageBitmap

data class Article(
    var id: String?,
    val imageUrl: String,
    val totalReads: Float,
    val type: String,
    val englishTitle: String,
    val chineseIntroduction: String,
    val level: String,
    val totalWords: Int,
    val totalComments: Int,
    val difficulty: String,
    var imageBitmap: ImageBitmap?
)