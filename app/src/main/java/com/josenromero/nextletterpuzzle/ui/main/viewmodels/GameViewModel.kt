package com.josenromero.nextletterpuzzle.ui.main.viewmodels

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.domain.player.AddOnePlayer
import com.josenromero.nextletterpuzzle.domain.player.GetPlayers
import com.josenromero.nextletterpuzzle.domain.player.UpdateOnePlayer
import com.josenromero.nextletterpuzzle.utils.getData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val context: Application,
    private val getPlayers: GetPlayers,
    private val addOnePlayer: AddOnePlayer,
    private val updateOnePlayer: UpdateOnePlayer
): ViewModel() {

    private val _players: MutableState<List<PlayerEntity>> = mutableStateOf(emptyList())
    val players: State<List<PlayerEntity>> get() = _players
    private val _data: MutableState<List<Item>> = mutableStateOf(emptyList())
    val data: State<List<Item>> get() = _data
    private val _isLoadingPlayer: MutableState<Boolean> = mutableStateOf(true)
    val isLoadingPlayer: State<Boolean> get() = _isLoadingPlayer

    init {
        getAllData()
        getAllPlayers()
    }

    private fun getAllData() {
        _data.value = getData(context, "data.json")
    }

    private fun getAllPlayers() {
        viewModelScope.launch(Dispatchers.IO) {

            val allPlayers = getPlayers()

            withContext(Dispatchers.Main) {
                _players.value = allPlayers
                if(allPlayers.isEmpty()) {
                    createPlayer(PlayerEntity(0, "userDefault", 1))
                } else {
                    _isLoadingPlayer.value = false
                }
            }

        }
    }

    fun createPlayer(player: PlayerEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addOnePlayer(player)
            getAllPlayers()
        }
    }

    fun updatePlayer(player: PlayerEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val nextLevel = player.currentLevel + 1
            val newValue = PlayerEntity(player.uid, player.name, nextLevel, player.achievements)
            updateOnePlayer(newValue)
            getAllPlayers()
        }
    }

}