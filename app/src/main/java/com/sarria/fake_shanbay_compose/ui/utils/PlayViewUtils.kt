package com.sarria.fake_shanbay_compose.ui.utils

import android.os.Bundle
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.sarria.fake_shanbay_compose.R

@Composable
fun rememberVideoViewWithLifecycle(): VideoView {
    val context = LocalContext.current

    val videoView = remember {
        object : VideoView(context) {
            override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
                val width = getDefaultSize(0, widthMeasureSpec)
                val height = getDefaultSize(0, heightMeasureSpec)
                setMeasuredDimension(width, height)
            }
        }.apply {
            id = R.id.video
        }
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(key1 = lifecycle) {
        val videoLifecycleObserve = getVideoLifecycleObserver(videoView)
        lifecycle.addObserver(videoLifecycleObserve)
        onDispose {
            lifecycle.removeObserver(videoLifecycleObserve)
        }
    }
    return videoView
}

private fun getVideoLifecycleObserver(videoView: VideoView): LifecycleObserver =
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> videoView.start()
            Lifecycle.Event.ON_PAUSE -> videoView.stopPlayback()
            else -> throw IllegalStateException()
        }
    }
