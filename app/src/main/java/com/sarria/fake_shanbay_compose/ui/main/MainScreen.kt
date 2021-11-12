package com.sarria.fake_shanbay_compose.ui.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sarria.fake_shanbay_compose.ui.commonLayout.noRippleClickable
import com.sarria.fake_shanbay_compose.navigation.BottomBarScreen
import com.sarria.fake_shanbay_compose.navigation.BottomNavGraph


/**
 * 主界面
 */
@Composable
fun MainScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val navHostController = rememberAnimatedNavController()
        Column {
            BottomNavGraph(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                navController = navHostController
            )
            BottomNavigationBar(navHostController)
        }
    }
}

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {

    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    val backgroundColor =
        if (isSystemInDarkTheme()) Color(0xFF181818) else MaterialTheme.colors.background
    Surface(elevation = 2.dp, color = backgroundColor) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(top = 8.dp, bottom = 8.dp)
                .height(48.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            BottomBarScreen.values().forEach { screen ->
                BottomItem(
                    screen = screen,
                    isSelected = currentDestination?.hierarchy?.any { screen.route == it.route } == true
                ) {
                    navHostController.navigate(screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                }
            }
        }
    }
}

@Composable
fun BottomItem(
    screen: BottomBarScreen,
    isSelected: Boolean,
    onclick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f
    )
    val alpha by animateFloatAsState(targetValue = if (isSelected) .7f else .3f)
    Column(
        modifier = Modifier
            .noRippleClickable { onclick() }
            .width(64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompositionLocalProvider(LocalContentAlpha provides alpha) {
            Icon(
                modifier = Modifier.size(20.dp * scale),
                painter = painterResource(id = screen.iconId),
                contentDescription = screen.title
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = screen.title, fontSize = 13.sp * scale)
        }
    }
}

