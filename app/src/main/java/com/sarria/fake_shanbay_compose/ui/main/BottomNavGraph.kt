package com.sarria.fake_shanbay_compose.ui.main

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.sarria.fake_shanbay_compose.ui.main.bookTable.BookTableScreen
import com.sarria.fake_shanbay_compose.ui.main.course.CourseScreen
import com.sarria.fake_shanbay_compose.ui.main.find.FindScreen
import com.sarria.fake_shanbay_compose.ui.main.home.HomeScreen
import com.sarria.fake_shanbay_compose.ui.main.my.MyScreen

@Composable
fun BottomNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {

    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(
            route = BottomBarScreen.Home.route,
            enterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            exitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popExitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            }
        ) {
            HomeScreen(modifier = Modifier.fillMaxSize())
        }
        composable(
            route = BottomBarScreen.BookTable.route,
            enterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            exitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popExitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            }
        ) {
            BookTableScreen(modifier = Modifier.fillMaxSize())
        }
        composable(route = BottomBarScreen.Course.route, enterTransition = {
            val initScreen = initialState.destination.bottomBarScreen() ?: return@composable null
            val targetScreen = targetState.destination.bottomBarScreen() ?: return@composable null
            when {
                targetScreen > initScreen -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                )
                targetScreen < initScreen -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                )
                else -> null
            }
        },
            exitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popExitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            }) {
            CourseScreen(modifier = Modifier.fillMaxSize())
        }
        composable(route = BottomBarScreen.Find.route, enterTransition = {
            val initScreen = initialState.destination.bottomBarScreen() ?: return@composable null
            val targetScreen = targetState.destination.bottomBarScreen() ?: return@composable null
            when {
                targetScreen > initScreen -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                )
                targetScreen < initScreen -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                )
                else -> null
            }
        },
            exitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popExitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            }) {
            FindScreen(modifier = Modifier.fillMaxSize())
        }
        composable(route = BottomBarScreen.My.route, enterTransition = {
            val initScreen = initialState.destination.bottomBarScreen() ?: return@composable null
            val targetScreen = targetState.destination.bottomBarScreen() ?: return@composable null
            when {
                targetScreen > initScreen -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                )
                targetScreen < initScreen -> slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                )
                else -> null
            }
        },
            exitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popEnterTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            },
            popExitTransition = {
                val initScreen =
                    initialState.destination.bottomBarScreen() ?: return@composable null
                val targetScreen =
                    targetState.destination.bottomBarScreen() ?: return@composable null
                when {
                    targetScreen > initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    targetScreen < initScreen -> slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(durationMillis = 500,delayMillis = 0,easing = LinearOutSlowInEasing)
                    )
                    else -> null
                }
            }) {
            MyScreen(modifier = Modifier.fillMaxSize())
        }
    }
}