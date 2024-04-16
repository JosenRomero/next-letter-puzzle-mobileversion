package com.josenromero.nextletterpuzzle.utils

import com.josenromero.nextletterpuzzle.data.SimpleAchievement

fun checkAchievementUnlocked(currentLevel: Int): SimpleAchievement? {

    return Constants.simpleAchievements.find { achievement ->
        (achievement.completed + 1) == currentLevel
    }

}