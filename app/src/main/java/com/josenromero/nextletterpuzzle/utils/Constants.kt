package com.josenromero.nextletterpuzzle.utils

import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.AchievementAboutLevel
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity

object Constants {
    const val lastLevel = 25
    const val player_database = "player_database"
    const val player_table = "player_table"

    const val PREFERENCESKEY_HowToPlay = "key_howToPlay"
    const val PREFERENCES_DATASTORE = "dataStore"

    val achievementsIDs_basic: List<String> = listOf("1", "2", "3")

    val levels_with_secrets: List<Int> = listOf(9, 16, 18, 21)

    val achievementsAboutLevel: List<AchievementAboutLevel> = listOf(
        AchievementAboutLevel("1", R.drawable.achievement_easy, "Dificultad fácil", "Completa 7 niveles", 7+1),
        AchievementAboutLevel("2", R.drawable.achievement_normal, "Dificultad normal", "Completa 18 niveles", 18+1),
        AchievementAboutLevel("3", R.drawable.achievement_hard, "Dificultad difícil", "Completa 24 niveles", 24+1),
        AchievementAboutLevel("4", R.drawable.achievement_human_lvl9, "Secretos. Dificultad fácil", "Encuentra todas las respuestas secretas del nivel 9", 9),
        AchievementAboutLevel("5", R.drawable.achievement_office_lvl16, "Secretos. Dificultad 2", "Encuentra todas las respuestas secretas del nivel 16", 16),
        AchievementAboutLevel("6", R.drawable.achievement_geometric_lvl18, "Secretos. Dificultad 3", "Encuentra todas las respuestas secretas del nivel 18", 18),
        AchievementAboutLevel("7", R.drawable.achievement_chemistry_lvl21, "Secretos. Dificultad difícil", "Encuentra todas las respuestas secretas del nivel 21", 21)
    )

    const val init_ads_in_level = 4

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