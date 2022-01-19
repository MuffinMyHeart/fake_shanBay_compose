package com.sarria.fake_shanbay_compose.ui.login

import android.net.Uri
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.accompanist.insets.systemBarsPadding
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.sarria.fake_shanbay_compose.R
import com.sarria.fake_shanbay_compose.ui.commonLayout.BackgroundSurface
import com.sarria.fake_shanbay_compose.ui.theme.LogoColor
import com.sarria.fake_shanbay_compose.ui.theme.VioletDark
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import kotlinx.coroutines.coroutineScope


@Composable
fun Login(onLoginEnd: () -> Unit = {}) {
    BackgroundSurface(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginContent(onLoginEnd)
    }
}

@Composable
fun LoginContent(onLoginEnd: () -> Unit) {

    ExoPlayerBackground()

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .padding(vertical = 28.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val loginAlphaState = remember {
            LoginAlphaState()
        }

        LaunchedEffect(key1 = Unit) {
            val animateSpec2000 = tween<Float>(
                durationMillis = 2000,
                easing = FastOutLinearInEasing
            )

            val animateSpec1500 = tween<Float>(
                durationMillis = 1500,
                easing = FastOutLinearInEasing
            )

            listOf(
                loginAlphaState.appbarAlpha,
                loginAlphaState.loginAlpha,
                loginAlphaState.otherLoginAlpha,
                loginAlphaState.agreementAlpha
            ).forEach {
                launch {
                    it.animateTo(1f, animateSpec2000)
                }
            }

            launch {
                delay(600)
                loginAlphaState.englishTextAlpha.animateTo(1f, animateSpec1500)
            }
            launch {
                delay(800)
                loginAlphaState.chineseTextAlpha.animateTo(1f, animateSpec1500)
            }
        }

        Column {
            LoginAppBar(
                modifier = Modifier
                    .alpha(loginAlphaState.appbarAlpha.value)
                    .fillMaxWidth()
                    .padding(start = 32.dp, end = 24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LoginText(
                modifier = Modifier.padding(horizontal = 32.dp),
                englishAlpha = loginAlphaState.englishTextAlpha.value,
                chineseAlpha = loginAlphaState.chineseTextAlpha.value
            )
        }

        Column {
            LoginRow(
                modifier = Modifier
                    .alpha(loginAlphaState.loginAlpha.value)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                onLoginEnd = onLoginEnd
            )
            Spacer(modifier = Modifier.height(24.dp))
            OtherLoginRow(
                modifier = Modifier
                    .alpha(loginAlphaState.otherLoginAlpha.value)
                    .padding(horizontal = 64.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(48.dp))
            BottomAgreement(
                modifier = Modifier
                    .alpha(loginAlphaState.agreementAlpha.value)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun BottomAgreement(modifier: Modifier) {
    Text(modifier = modifier, fontSize = 12.sp, color = Color.White, text = "同意条款")
}

@Composable
fun OtherLoginRow(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val cycleColor = VioletDark
        val cycleSize = 48.dp


        Image(
            modifier = Modifier
                .size(cycleSize)
                .clip(CircleShape)
                .background(cycleColor)
                .padding(12.dp),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ic_apple),
            contentDescription = "apple"
        )



        Image(
            modifier = Modifier
                .size(cycleSize)
                .clip(CircleShape)
                .background(cycleColor)
                .padding(12.dp),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ic_qq),
            contentDescription = "qq"
        )

        Image(
            modifier = Modifier
                .size(cycleSize)
                .clip(CircleShape)
                .background(cycleColor)
                .padding(12.dp),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ic_weibo),
            contentDescription = "weibo"
        )

        Image(
            modifier = Modifier
                .size(cycleSize)
                .clip(CircleShape)
                .background(cycleColor)
                .padding(12.dp),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ic_shanbay),
            contentDescription = "apple"
        )

    }
}

@Composable
fun LoginRow(modifier: Modifier, onLoginEnd: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LogoColor,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 8.dp),
            shape = RoundedCornerShape(64.dp),
            onClick = { onLoginEnd() }) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_phone),
                contentDescription = "wechat"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "手机号登录")
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White.copy(alpha = 0.35f),
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 8.dp),
            shape = RoundedCornerShape(64.dp),
            onClick = { onLoginEnd() }) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_wechat),
                contentDescription = "wechat"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "微信登录")
        }

    }
}

@Composable
fun LoginAppBar(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.mipmap.shanbay_logo),
            contentDescription = "Logo"
        )


        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            color = Color.White.copy(alpha = ContentAlpha.medium),
            text = "随便看看 >",
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun LoginText(modifier: Modifier, englishAlpha: Float, chineseAlpha: Float) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .alpha(englishAlpha)
                .width(280.dp),
            text = "Read what the world reads",
            color = Color.White.copy(0.9f),
            style = MaterialTheme.typography.h2,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .alpha(chineseAlpha)
                .width(240.dp),
            text = "扇贝阅读\n陪你一起, 读懂世界",
            color = Color.White.copy(0.83f),
            style = MaterialTheme.typography.h5
        )
    }
}


@Composable
fun ExoPlayerBackground() {
    val context = LocalContext.current
    var prepared by remember {
        mutableStateOf(false)
    }

    val player = remember {
        val loadControl =
            DefaultLoadControl.Builder()
                .setPrioritizeTimeOverSizeThresholds(false)
                .build()

        SimpleExoPlayer
            .Builder(context)
            .setLoadControl(loadControl)
            .build()
            .apply {
                repeatMode = Player.REPEAT_MODE_ONE
            }
    }

    val playerView: PlayerView = remember {
        PlayerView(context).apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
        }
    }

    SideEffect {
        val packageName = context.packageName
        val uri = Uri.parse("android.resource://$packageName/${R.raw.even}")
        playerView.useController = false
        player.setMediaItem(MediaItem.fromUri(uri))
        playerView.player = player
        player.prepare()
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == Player.STATE_READY) {
                    prepared = true
                }
            }
        })
        player.playWhenReady = true
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(player) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> player.play()
                Lifecycle.Event.ON_STOP -> player.pause()
            }
        }

        lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
            player.release()
        }
    }


    if (prepared) {
        AndroidView(
            factory = {
                playerView
            },
        )
    }
}

class LoginAlphaState {
    val appbarAlpha = Animatable(0f)
    val englishTextAlpha = Animatable(0f)
    val chineseTextAlpha = Animatable(0f)
    val loginAlpha = Animatable(0f)
    val otherLoginAlpha = Animatable(0f)
    val agreementAlpha = Animatable(0f)
}


class A{
   lateinit var a: A
}

fun a(){
    val a = A()
}