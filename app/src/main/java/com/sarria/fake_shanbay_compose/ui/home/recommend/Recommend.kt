package com.sarria.fake_shanbay_compose.ui.home.recommend

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun RecommendPage(modifier: Modifier = Modifier) {
    Column(modifier) {
        val transition = rememberInfiniteTransition()
        val scale by transition.animateFloat(
            initialValue = 1f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 3600
                    4.0f at 1800
                },
                repeatMode = RepeatMode.Restart
            )
        )

        Text(text = "hello")
        Text(modifier = Modifier
            .size(26.dp)
            .scale(scale)
            .offset(12.dp, 12.dp), text = "hello")
        Text(text = "hello")
    }
}

