package com.sarria.fake_shanbay_compose.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200,
//    onSurface = Color.Red,
//    background = Color.Red,
//    onBackground = Color.Red,
//)

@SuppressLint("ConflictingOnColor")
private val LightThemeColor = lightColors(
    background = Color.White,
    onBackground = Color.Black
)

@SuppressLint("ConflictingOnColor")
private val DarkThemeColor = darkColors(
    background = Color.Black,
    onBackground = Color.White
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
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}