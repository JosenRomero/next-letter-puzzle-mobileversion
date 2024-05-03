package com.josenromero.nextletterpuzzle.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.josenromero.nextletterpuzzle.utils.Constants
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val key_howToPlay = booleanPreferencesKey(Constants.PREFERENCESKEY_HowToPlay)
    }

    suspend fun getHowToPlay(): Boolean {
        return try {
            val preferences = dataStore.data.first()
            preferences[key_howToPlay] ?: false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun setHowToPlay(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[key_howToPlay] = value
        }
    }
}