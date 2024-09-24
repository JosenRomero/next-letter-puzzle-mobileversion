package com.josenromero.nextletterpuzzle.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppNavigation
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.UnityAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), IUnityAdsInitializationListener {

    private val unityGameID = "1234567" // ID for test
    private val testMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // Initialize the SDK:
        UnityAds.initialize(applicationContext, unityGameID, testMode, this)

        setContent {
            NextLetterPuzzleTheme {
                AppNavigation()
            }
        }

    }

    override fun onInitializationComplete() {
        println("onInitializationComplete")
    }

    override fun onInitializationFailed(
        error: UnityAds.UnityAdsInitializationError?,
        message: String?
    ) {
        println("Unity Ads initialization failed with error: $error $message")
    }

}
