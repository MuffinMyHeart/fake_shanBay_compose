package com.sarria.fake_shanbay_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sarria.fake_shanbay_compose.ui.theme.Fake_shanBay_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
            Fake_shanBay_composeTheme {
                val uiController = rememberSystemUiController()
                val useDarkIcon = MaterialTheme.colors.isLight
                //状态栏透明
                SideEffect {
                    uiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcon
                    )
                }

                ProvideWindowInsets {
                    ShanBayApp()
                }
            }
        }
    }
}