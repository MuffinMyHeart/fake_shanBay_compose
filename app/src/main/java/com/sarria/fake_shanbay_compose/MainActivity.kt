package com.sarria.fake_shanbay_compose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sarria.fake_shanbay_compose.ui.App
import com.sarria.fake_shanbay_compose.ui.commonLayout.BackgroundSurface
import com.sarria.fake_shanbay_compose.ui.splash.Splash
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


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
@Composable
fun ShanBayApp() {
    var splashed by rememberSaveable {
        mutableStateOf(false)
    }

    BackgroundSurface(modifier = Modifier.fillMaxSize()) {
        if (splashed) {
            App()
        } else {
            Splash {
                splashed = true
            }
        }
    }
}