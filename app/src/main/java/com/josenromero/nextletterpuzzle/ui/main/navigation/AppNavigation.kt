package com.josenromero.nextletterpuzzle.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.nextletterpuzzle.ui.main.viewmodels.GameViewModel
import com.josenromero.nextletterpuzzle.ui.main.views.HomeScreen
import com.josenromero.nextletterpuzzle.ui.main.views.PlayScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val gameViewModel: GameViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                players = gameViewModel.players.value,
                data = gameViewModel.data.value
            )
        }
        composable(route = AppScreens.PlayScreen.route) {
            PlayScreen()
        }
    }

}