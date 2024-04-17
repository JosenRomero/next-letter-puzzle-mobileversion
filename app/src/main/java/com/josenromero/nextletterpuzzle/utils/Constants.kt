package com.josenromero.nextletterpuzzle.utils

import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.AchievementAboutLevel
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity

object Constants {
    const val lastLevel = 4
    const val player_database = "player_database"
    const val player_table = "player_table"

    val achievementsAboutLevel: List<AchievementAboutLevel> = listOf(
        AchievementAboutLevel("1", R.drawable.achievement_easy, "Fácil", "Completa 7 niveles", 7),
        AchievementAboutLevel("2", R.drawable.achievement_normal, "Normal", "Completa 18 niveles", 18),
        AchievementAboutLevel("3", R.drawable.achievement_hard, "Difícil", "Completa 24 niveles", 24)
    )

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