package com.josenromero.nextletterpuzzle.ui.main.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.nextletterpuzzle.domain.preferences.GetHowToPlay
import com.josenromero.nextletterpuzzle.domain.preferences.SetHowToPlay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val getHowToPlay: GetHowToPlay,
    private val setHowToPlay: SetHowToPlay
): ViewModel() {

    private val _showHowToPlay: MutableState<Boolean?> = mutableStateOf(null)
    val showHowToPlay: State<Boolean?> get() = _showHowToPlay

    init {
        getValueHowToPlay()
    }

    private fun getValueHowToPlay() {
        viewModelScope.launch {
            _showHowToPlay.value = getHowToPlay()
        }
    }

    fun setValueHowToPlay(value: Boolean) {
        viewModelScope.launch {
            setHowToPlay(value)
            _showHowToPlay.value = value
        }
    }
}