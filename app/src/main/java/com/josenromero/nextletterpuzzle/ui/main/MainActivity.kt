package com.josenromero.nextletterpuzzle.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppNavigation
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            NextLetterPuzzleTheme {
                AppNavigation()
            }
        }
    }
}
