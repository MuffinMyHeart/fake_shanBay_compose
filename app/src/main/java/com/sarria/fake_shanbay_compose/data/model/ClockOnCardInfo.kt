package com.sarria.fake_shanbay_compose.data.model

import androidx.compose.ui.graphics.ImageBitmap

data class ClockOnCardInfo(
    val totalDays: Int,
    val todayReads: Int,
    val totalJoiner: Int,
    val headerUrls: List<String>,
    var imageBitmaps: List<ImageBitmap?>
)