package com.josenromero.nextletterpuzzle.ui.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.nextletterpuzzle.ui.main.viewmodels.GameViewModel
import com.josenromero.nextletterpuzzle.ui.main.views.AchievementsScreen
import com.josenromero.nextletterpuzzle.ui.main.views.EndScreen
import com.josenromero.nextletterpuzzle.ui.main.views.HomeScreen
import com.josenromero.nextletterpuzzle.ui.main.views.PlayScreen
import com.josenromero.nextletterpuzzle.utils.Constants

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val gameViewModel: GameViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(
            route = AppScreens.HomeScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    AppScreens.PlayScreen.route,
                    AppScreens.EndScreen.route ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(1500)
                        )
                    AppScreens.AchievementsScreen.route ->
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Down,
                            animationSpec = tween(1500)
                        )
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    AppScreens.PlayScreen.route ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                            animationSpec = tween(1500)
                        )
                    AppScreens.AchievementsScreen.route ->
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(1500)
                        )

                    else -> null
                }
            }
        ) {
            HomeScreen(
                onNavigateToAScreen = {  route -> navController.navigate(route) },
                players = gameViewModel.players.value
            )
        }
        composable(
            route = AppScreens.PlayScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1500)
                )
            }
        ) {
            PlayScreen(
                data = gameViewModel.data.value,
                player = gameViewModel.players.value[0],
                isLoading = gameViewModel.isLoadingPlayer.value,
                lastLevel = Constants.lastLevel == gameViewModel.players.value[0].currentLevel,
                onNavigateToAScreen = { route -> navController.navigate(route)},
                nextLevelBtn = { player ->
                    gameViewModel.updatePlayer(player)
                    navController.navigate(AppScreens.PlayScreen.route)
                },
                lastLevelCompleteBtn = { player ->
                    gameViewModel.updatePlayer(player)
                    navController.navigate(AppScreens.EndScreen.route)
                }
            )
        }
        composable(
            route = AppScreens.AchievementsScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(1500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(1500)
                )
            }
        ) {
            AchievementsScreen(
                player = gameViewModel.players.value[0],
                onNavigateToBack = { navController.popBackStack() }
            )
        }
        composable(
            route = AppScreens.EndScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1500)
                )
            }
        ) {
            EndScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(AppScreens.HomeScreen.route)
                }
            )
        }
    }

}