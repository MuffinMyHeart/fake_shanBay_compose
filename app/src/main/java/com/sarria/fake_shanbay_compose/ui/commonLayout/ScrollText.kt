package com.sarria.fake_shanbay_compose.ui.commonLayout

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//滚动图标
@ExperimentalAnimationApi
@Composable
fun VerticalScrollText(
    modifier: Modifier = Modifier,
    scrollList: List<String>,
    textStyle: TextStyle = LocalTextStyle.current,
    onClick: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf(scrollList[0])
    }


    LaunchedEffect(key1 = Unit) {
        launch {
            var index = 0
            while (true) {
                delay(3000)
                index = (index + 1) % scrollList.size
                text = scrollList[index]
            }
        }
    }

    AnimatedContent(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onClick(text) })
            .then(modifier),
        targetState = text,
        transitionSpec = {
            slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(500)
            ) with slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(500)
            )
        }
    ) { targetText ->
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = targetText,
                style = textStyle,
            )
        }
    }
}