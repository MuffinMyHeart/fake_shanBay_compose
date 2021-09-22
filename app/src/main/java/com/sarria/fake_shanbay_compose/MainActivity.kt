package com.sarria.fake_shanbay_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.sarria.fake_shanbay_compose.ui.home.Main
import com.sarria.fake_shanbay_compose.ui.splash.Splash
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Fake_shanBay_composeTheme {
                ProvideWindowInsets {
                    ShanBayApp()
                }
            }
        }
    }
}


//是闪屏页还是主调用流程
@ExperimentalAnimationApi
@Composable
fun ShanBayApp() {
    var splashed by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Crossfade(splashed) {
            if (it) {
                Main()
            } else {
                Splash { splashed = true }
            }
        }
    }
}