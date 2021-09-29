package com.sarria.fake_shanbay_compose.ui.home.recommend

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarria.fake_shanbay_compose.ui.theme.DarkRed

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
                    5.0f at 1800
                },
                repeatMode = RepeatMode.Restart
            )
        )

        Text(text = "hello")
        Surface(
            modifier = Modifier
                .size(16.dp)
                .scale(scale)
                .offset(10.dp, (-6).dp),
            shape = RoundedCornerShape(16.dp),
            color = DarkRed,
            contentColor = Color.White
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    text = "5"
                )
            }
        }
        Text(text = "hello")
    }
}

