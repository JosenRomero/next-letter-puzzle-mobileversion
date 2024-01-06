package com.josenromero.nextletterpuzzle.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.nextletterpuzzle.ui.main.viewmodels.GameViewModel
import com.josenromero.nextletterpuzzle.ui.main.views.HomeScreen
import com.josenromero.nextletterpuzzle.ui.main.views.PlayScreen
import com.josenromero.nextletterpuzzle.utils.Constants

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val gameViewModel: GameViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(
                onNavigateToAScreen = {  route -> navController.navigate(route) },
                players = gameViewModel.players.value
            )
        }
        composable(route = AppScreens.PlayScreen.route) {
            PlayScreen(
                currentData = gameViewModel.data.value[0],
                player = Constants.playerFake,
                checkAnswer = {}
            )
        }
    }

}