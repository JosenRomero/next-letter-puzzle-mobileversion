package com.josenromero.nextletterpuzzle.ui.main.navigation

sealed class AppScreens(val route: String) {
    object HomeScreen: AppScreens("HomeScreen")
    object PlayScreen: AppScreens("PlayScreen")
    object EndScreen: AppScreens("EndScreen")
    object AchievementsScreen: AppScreens("AchievementsScreen")
    object AboutScreen: AppScreens("AboutScreen")
}