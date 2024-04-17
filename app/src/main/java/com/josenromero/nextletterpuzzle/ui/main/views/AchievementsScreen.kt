package com.josenromero.nextletterpuzzle.ui.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.Achievement
import com.josenromero.nextletterpuzzle.ui.components.SimpleTopAppBar
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

@Composable
fun AchievementsScreen(
    player: PlayerEntity,
    onNavigateToBack: () -> Unit
) {

    val currentLevel = player.currentLevel

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = "Logros",
                onNavigateToAScreen = { onNavigateToBack() }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(Constants.achievementsAboutLevel) { achievement ->
                    Achievement(
                        imgId = achievement.imgId,
                        title = achievement.title,
                        goal = achievement.goal,
                        completed = currentLevel > achievement.completedLevel
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AchievementsScreenPreview() {
    NextLetterPuzzleTheme {
        AchievementsScreen(
            player = Constants.playerFake,
            onNavigateToBack = {}
        )
    }
}