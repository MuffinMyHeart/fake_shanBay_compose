package com.sarria.fake_shanbay_compose.ui.splash


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.sarria.fake_shanbay_compose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//闪屏页
@Composable
fun Splash(onSplashEnd: () -> Unit) {
//    val initColor = MaterialTheme.colors.surface
//    val targetColor = MaterialTheme.colors.background
//    val color = remember {
//        Animatable(initColor)
//    }
//    LaunchedEffect(key1 = Unit) {
//        launch {
//            color.animateTo(
//                targetValue = targetColor,
//                animationSpec = tween(1000)
//            )
//        }
//    }
    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = color.value
    ) {
        SplashContent(onSplashEnd)
    }
}

@Composable
private fun SplashContent(onSplashEnd: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val scale = remember {
            Animatable(0f)
        }

        val alpha = remember {
            Animatable(1f)
        }

        val rotation = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = Unit) {
            val animationTime = 1000
            //1秒后 页面结束
            launch {
                delay(animationTime.toLong())
                onSplashEnd()
            }
            launch {
                scale.animateTo(
                    targetValue = 0f,
                    animationSpec = keyframes {
                        durationMillis = animationTime
                        1f at animationTime / 2
                    })
            }
            launch {
                alpha.animateTo(
                    0f,
                    animationSpec = tween(
                        durationMillis = animationTime,
                        easing = FastOutLinearInEasing
                    )
                )
            }
            launch {
                rotation.animateTo(
                    targetValue = 360f,
                    animationSpec = tween(
                        durationMillis = animationTime / 2
                    )
                )
            }
        }

        Image(
            modifier = Modifier
                .scale(scale = scale.value)
                .alpha(alpha = alpha.value)
                .rotate(degrees = rotation.value),
            painter = painterResource(id = R.mipmap.compose_logo),
            contentDescription = "compose logo"
        )
    }
}

