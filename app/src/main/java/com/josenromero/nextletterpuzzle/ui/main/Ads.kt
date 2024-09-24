package com.josenromero.nextletterpuzzle.ui.main

import android.app.Activity
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsShowOptions

object Ads {

    private const val adUnitId = "Interstitial_Android"

    val showListener = object : IUnityAdsShowListener {
        override fun onUnityAdsShowFailure(
            placementId: String?,
            error: UnityAds.UnityAdsShowError?,
            message: String?
        ) {
            println("Unity Ads failed to show ad for $placementId with error: $error $message")
        }

        override fun onUnityAdsShowStart(placementId: String?) {
            println("onUnityAdsShowStart: $placementId")
        }

        override fun onUnityAdsShowClick(placementId: String?) {
            println("onUnityAdsShowClick: $placementId")
        }

        override fun onUnityAdsShowComplete(
            placementId: String?,
            state: UnityAds.UnityAdsShowCompletionState?
        ) {
            println("onUnityAdsShowComplete: $placementId")
        }
    }

    // load an interstitial ad. The ad will start to show after the ad has been loaded.
    fun displayInterstitialAd(activity: Activity) {
        UnityAds.load(adUnitId, object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String?) {
                UnityAds.show(activity, placementId, UnityAdsShowOptions(), showListener)
            }

            override fun onUnityAdsFailedToLoad(
                placementId: String?,
                error: UnityAds.UnityAdsLoadError?,
                message: String?
            ) {
                println("Unity Ads failed to load ad for $placementId with error: $error $message")
            }

        })
    }

}