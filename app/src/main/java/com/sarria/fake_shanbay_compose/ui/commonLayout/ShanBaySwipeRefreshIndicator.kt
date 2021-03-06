package com.sarria.fake_shanbay_compose.ui.commonLayout

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun ShanBaySwipeRefreshIndicator(
    state: SwipeRefreshState,
    refreshTriggerDistance: Dp,
    modifier: Modifier = Modifier,
    fade: Boolean = true,
    scale: Boolean = false,
    arrowEnabled: Boolean = true,
    backgroundColor: Color = Color.White,
    contentColor: Color = MaterialTheme.colors.primary,
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
    refreshingOffset: Dp = 64.dp,
    largeIndication: Boolean = false,
    elevation: Dp = 6.dp,
) {
    SwipeRefreshIndicator(
        state = state,
        refreshTriggerDistance = refreshTriggerDistance,
        modifier = modifier,
        fade = fade,
        scale = scale,
        arrowEnabled = arrowEnabled,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        shape = shape,
        refreshingOffset = refreshingOffset,
        largeIndication = largeIndication,
        elevation = elevation
    )
}