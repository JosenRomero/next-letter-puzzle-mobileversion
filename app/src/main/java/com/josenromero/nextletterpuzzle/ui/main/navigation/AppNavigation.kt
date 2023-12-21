package com.josenromero.nextletterpuzzle.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.josenromero.nextletterpuzzle.ui.main.views.HomeScreen
import com.josenromero.nextletterpuzzle.ui.main.views.PlayScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = AppScreens.PlayScreen.route) {
            PlayScreen()
        }
    }

}