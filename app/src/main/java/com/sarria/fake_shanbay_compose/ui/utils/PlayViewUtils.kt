package com.sarria.fake_shanbay_compose.ui.utils

import android.media.MediaPlayer
import android.net.Uri
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



private fun getVideoLifecycleObserver(videoView: VideoView): LifecycleObserver {
    val observer = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> videoView.start()
            Lifecycle.Event.ON_PAUSE ->  videoView.stopPlayback()

        }
    }
    return observer
}


//@Composable
//fun rememberVideoViewWithLifecycle(uri: Uri): VideoView {
//    val context = LocalContext.current
//
//    val videoView = remember {
//        object : VideoView(context) {
//            override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//                val width = getDefaultSize(0, widthMeasureSpec)
//                val height = getDefaultSize(0, heightMeasureSpec)
//                setMeasuredDimension(width, height)
//            }
//        }.apply {
//            id = R.id.video
//        }
//    }
//
//
//    val lifecycle = LocalLifecycleOwner.current.lifecycle
//    videoView.setVideoURI(uri)
//    videoView.setOnCompletionListener {
//        it.start()
//    }
//
//    DisposableEffect(key1 = lifecycle) {
//        var videoLifecycleObserve: LifecycleObserver? = null
//        videoView.setOnPreparedListener {
//            val localVideoLifecycleObserve = getVideoLifecycleObserver(it)
//            videoLifecycleObserve = localVideoLifecycleObserve
//            lifecycle.addObserver(localVideoLifecycleObserve)
//        }
//        onDispose {
//            if (videoLifecycleObserve!=null){
//                lifecycle.removeObserver(videoLifecycleObserve!!)
//            }
//        }
//    }
//    return videoView
//}
//
//private fun getVideoLifecycleObserver(mediaPlayer: MediaPlayer): LifecycleObserver {
//    return LifecycleEventObserver { _, event ->
//        when (event) {
//            Lifecycle.Event.ON_RESUME -> {
////                mediaPlayer.reset()
//                mediaPlayer.start()
//            }
//            Lifecycle.Event.ON_PAUSE -> mediaPlayer.pause()
//            else -> {
//            }
//        }
//    }
//}

