package com.josenromero.nextletterpuzzle.data.player

import javax.inject.Inject

class PlayerRepository @Inject constructor(
    private val playerDao: PlayerDao
) {

    fun getPlayers(): List<PlayerEntity> {
        return playerDao.getPlayers()
    }

    fun addOnePlayer(player: PlayerEntity) {
        playerDao.addOnePlayer(player)
    }

    fun deleteOnePlayer(player: PlayerEntity) {
        playerDao.deleteOnePlayer(player)
    }

    fun updateOnePlayer(player: PlayerEntity) {
        playerDao.updateOnePlayer(player)
    }
}