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
import com.sarria.fake_shanbay_compose.ui.Main
import com.sarria.fake_shanbay_compose.ui.login.Login
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


//should login or splash
@ExperimentalAnimationApi
@Composable
fun ShanBayApp() {
    var login by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
            if (!login) {
                Login{login = true}
            } else {
                Splash {

                }
            }
    }
}