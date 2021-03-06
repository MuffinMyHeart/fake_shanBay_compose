package com.sarria.fake_shanbay_compose.ui.main.bookTable

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import kotlin.math.*

const val TOP_BAR_MAX_HEIGHT = 120f
const val TOP_BAR_MIN_HEIGHT = 64f

enum class TopBarState {
    EXPANDED,
    COLLAPSED
}


@Composable
fun BookTableScreen(modifier: Modifier = Modifier) {
//    ProfilePage()
//    MessengerIcon()
    CustomIcon()
//    VerticalDashLine(modifier = Modifier.fillMaxSize())
}

@Composable
fun CustomIcon() {
    val transition = rememberInfiniteTransition()
    val degree by transition.animateFloat(
        initialValue = 0f,
        targetValue = -360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing)
        )
    )
    val sineWaveSamplePath = remember {
        Path()
    }

    val gradient1 = remember {
        listOf(Color(0xFF2078EE), Color(0xFF74E6FE))
    }
    val gradient2 = remember {
        listOf(Color(0xFFFFC200), Color(0xFFFFE100))
    }
    val gradient3 = remember{
        listOf(Color(0xFF02b8f9), Color(0xFF0277fe))
    }

    Canvas(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val radius = size.minDimension / 2.0f - 30f
        val radian = degree / 180 * PI
        val projectionX = (cos(radian) * radius).toFloat()
        val projectionY = (sin(radian) * radius).toFloat()
        val height = size.height
        val width = size.width

        drawLine(
            color = Color.Gray,
            start = Offset(width / 2, 0f),
            end = Offset(width / 2, height),
            strokeWidth = 10f
        )

        drawLine(
            color = Color.Gray,
            start = Offset(0f, height / 2),
            end = Offset(width, height / 2),
            strokeWidth = 10f
        )

        drawLine(
            color = Color.Gray,
            start = Offset(0f, height / 4 * 3),
            end = Offset(width, height / 4 * 3),
            strokeWidth = 10f
        )

        translate(width / 2, height / 4 * 3) {
            drawCircle(
                brush = Brush.sweepGradient(gradient3),
                radius = radius,
                style = Stroke(
                    width = 16f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                ),
                center = Offset(0f, 0f)
            )
        }

        rotate(degree, Offset(width / 2, height / 4 * 3)) {
            drawLine(
                brush = Brush.sweepGradient(gradient2),
                start = Offset(width / 2, height / 4 * 3),
                end = Offset(width / 2 + radius, height / 4 * 3),
                strokeWidth = 10f
            )
        }

        drawLine(
            color = Color.Blue,
            start = Offset(width / 2 + projectionX, height / 2),
            end = Offset(width / 2 + projectionX, height / 4 * 3 + projectionY),
            strokeWidth = 10f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
        )

        drawLine(
            color = Color.White,
            start = Offset(width / 2 + projectionX, height / 4 * 3),
            end = Offset(width / 2 + projectionX, height / 4 * 3 + projectionY),
            strokeWidth = 10f,
        )

        drawCircle(
            color = Color.White,
            radius = 10f,
            center = Offset(width / 2 + projectionX, height / 2)
        )

        drawCircle(
            color = Color.White,
            radius = 10f,
            center = Offset(
                width / 2 + projectionX,
                height / 4 * 3 + projectionY
            )
        )

        translate(width / 2, height / 2) {
            val samplesCount = 80
            val dy = height / 2 / samplesCount
            sineWaveSamplePath.reset()
            sineWaveSamplePath.moveTo(projectionX, 0f)
            repeat(samplesCount) {
                val x = radius * cos(it * 0.15 + radian).toFloat()
                val y = -dy * it
                sineWaveSamplePath.quadraticBezierTo(x, y, x, y)
            }
            drawPath(
                path = sineWaveSamplePath,
                brush = Brush.sweepGradient(gradient1),
                style = Stroke(width = 10f)
            )
        }

    }
}

@Composable
fun VerticalDashLine(modifier: Modifier) {
    Canvas(
        modifier = Modifier
            .widthIn(min = 16.dp)
            .then(modifier)
    ) {
        drawLine(
            color = Color(0xFFBEC0C4),
            start = Offset(size.width / 2, 0f),
            end = Offset(size.width / 2, size.height.times(.8f)),
            strokeWidth = 4f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )

        drawLine(
            brush = Brush.linearGradient(
                listOf(
                    Color(0xFFBEC0C4),
                    Color(0xFFBEC0C4),
                    Color(0x4DBEC0C4)
                )
            ),
            start = Offset(size.width / 2, size.height.times(.8f)),
            end = Offset(size.width / 2, size.height),
            strokeWidth = 4f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )

    }
}