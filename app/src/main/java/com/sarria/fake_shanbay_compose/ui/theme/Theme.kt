package com.sarria.fake_shanbay_compose.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("ConflictingOnColor")
private val LightThemeColor = lightColors(
    primary = Color(0xFF90C060),
    onPrimary = Color.White,
    surface = Color(0xFFF0F0F0),
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black
)

@SuppressLint("ConflictingOnColor")
private val DarkThemeColor = darkColors(
    primary = Color(0xFF57703A),
    onPrimary = Color.White,
    surface = Color.Black,
    onSurface = Color(0xFFA0A0A0),
    background = Color(0xFF202020),
    onBackground = Color(0xFFA0A0A0)
)

@Composable
fun Fake_shanBay_composeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkThemeColor
    } else {
        LightThemeColor
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
    ) {

        val uiController = rememberSystemUiController()
        val useDarkIcon = MaterialTheme.colors.isLight
        //状态栏透明
        SideEffect {
            uiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcon
            )
        }

        content()
    }
}