package com.josenromero.nextletterpuzzle.utils

import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity

object Constants {
    const val player_database = "player_database"
    const val player_table = "player_table"

    var playerFake = PlayerEntity(0, "Jose Romero", 1, emptyList())

    var dataFake: List<Item> = listOf(
        Item(
            topic = "Frutas",
            letters = arrayListOf("a", "e", "i", "k", "m", "n", "p", "r", "w", "z"),
            answer = arrayListOf("pera", "manzana", "kiwi"),
            validAnswer = arrayListOf()
        )
    )

}