package com.josenromero.nextletterpuzzle.domain.player

import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.data.player.PlayerRepository
import javax.inject.Inject

class GetPlayers @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(): List<PlayerEntity> {
        return playerRepository.getPlayers()
    }
}