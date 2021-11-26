package com.sarria.fake_shanbay_compose.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sarria.fake_shanbay_compose.ui.main.bookTable.BookTableScreen
import com.sarria.fake_shanbay_compose.ui.main.course.CourseScreen
import com.sarria.fake_shanbay_compose.ui.main.find.FindScreen
import com.sarria.fake_shanbay_compose.ui.main.home.HomeScreen
import com.sarria.fake_shanbay_compose.ui.main.my.MyScreen

@Composable
fun BottomNavGraph(modifier: Modifier, navController: NavHostController) {

    val enterTransition: (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?)? = {
        val initScreen =
            initialState.destination.bottomBarScreen()
        val targetScreen =
            targetState.destination.bottomBarScreen()
        when {
            targetScreen > initScreen -> slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = 0,
                    easing = LinearOutSlowInEasing
                )
            )
            targetScreen < initScreen -> slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = 0,
                    easing = LinearOutSlowInEasing
                )
            )
            else -> null
        }
    }

    val exitTransition: (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?) = {
        val initScreen =
            initialState.destination.bottomBarScreen()
        val targetScreen =
            targetState.destination.bottomBarScreen()
        when {
            targetScreen > initScreen -> slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = 0,
                    easing = LinearOutSlowInEasing
                )
            )
            targetScreen < initScreen -> slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(
                    durationMillis = 500,
                    delayMillis = 0,
                    easing = LinearOutSlowInEasing
                )
            )
            else -> null
        }
    }

    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(
            route = BottomBarScreen.Home.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
        ) {
            HomeScreen(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = BottomBarScreen.BookTable.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
        ) {
            BookTableScreen(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = BottomBarScreen.Course.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
        ) {
            CourseScreen(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = BottomBarScreen.Find.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
        ) {
            FindScreen(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = BottomBarScreen.My.route,
            enterTransition = enterTransition,
            exitTransition = exitTransition,
        ) {
            MyScreen(modifier = Modifier.fillMaxSize())
        }
    }
}