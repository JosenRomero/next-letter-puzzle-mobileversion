package com.josenromero.nextletterpuzzle.domain.player

import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.data.player.PlayerRepository
import javax.inject.Inject

class UpdateOnePlayer @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend operator fun invoke(player: PlayerEntity) {
        playerRepository.updateOnePlayer(player)
    }
}