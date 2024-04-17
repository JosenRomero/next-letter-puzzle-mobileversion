package com.josenromero.nextletterpuzzle.utils

import com.josenromero.nextletterpuzzle.data.AchievementAboutLevel

fun checkAchievementUnlocked(currentLevel: Int): AchievementAboutLevel? {

    return Constants.achievementsAboutLevel.find { achievement ->
        (achievement.completedLevel + 1) == currentLevel
    }

}