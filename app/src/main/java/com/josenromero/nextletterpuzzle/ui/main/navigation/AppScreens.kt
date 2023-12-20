package com.josenromero.nextletterpuzzle.ui.main.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("HomeScreen")
}