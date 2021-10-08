package com.sarria.fake_shanbay_compose

import android.content.Context
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sarria.fake_shanbay_compose.ui.Main
import com.sarria.fake_shanbay_compose.ui.commonLayout.BackgroundSurface
import com.sarria.fake_shanbay_compose.ui.home.Home
import com.sarria.fake_shanbay_compose.ui.login.Login
import com.sarria.fake_shanbay_compose.ui.splash.Splash
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme


val Context.datastore :DataStore<Preferences> by preferencesDataStore(name = "login")
class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
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
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun ShanBayApp() {
    var splashed by rememberSaveable {
        mutableStateOf(false)
    }

//    var login by rememberSaveable {
//        mutableStateOf(false)
//    }
//
//    Surface(modifier = Modifier.fillMaxSize()) {
//        if (!login){
//            Login{
//                login = true
//                splashed = true
//            }
//        } else if (!splashed){
//            Splash { splashed =true }
//        } else{
//            Home()
//        }
//    }
    BackgroundSurface(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = splashed) {
            if (it){
                Main()
            } else {
                Splash {
                    splashed = true
                }
            }
        }
    }
}