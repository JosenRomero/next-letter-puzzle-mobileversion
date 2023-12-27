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
    private val addOnePlayer: AddOnePlayer
): ViewModel() {

    private val _players: MutableState<List<PlayerEntity>> = mutableStateOf(emptyList())
    val players: State<List<PlayerEntity>> get() = _players
    private val _data: MutableState<List<Item>> = mutableStateOf(emptyList())
    val data: State<List<Item>> get() = _data

    init {
        getAllData()
        getAllPlayers()
    }

    private fun getAllData() {
        _data.value = getData(context, "data.json")
        println("My data: $data")
    }

    private fun getAllPlayers() {
        viewModelScope.launch(Dispatchers.IO) {

            val allPlayers = getPlayers()

            withContext(Dispatchers.Main) {
                _players.value = allPlayers
            }

        }
    }

    fun createPlayer(player: PlayerEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addOnePlayer(player)
        }
    }

}