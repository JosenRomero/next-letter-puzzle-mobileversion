package com.josenromero.nextletterpuzzle.data.preferences

import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val userPreferences: UserPreferences
) {

    suspend fun getHowToPlay(): Boolean {
        return userPreferences.getHowToPlay()
    }

    suspend fun setHowToPlay(value: Boolean) {
        userPreferences.setHowToPlay(value)
    }
}