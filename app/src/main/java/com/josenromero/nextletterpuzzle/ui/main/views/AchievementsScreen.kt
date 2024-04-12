package com.josenromero.nextletterpuzzle.ui.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.Achievement
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

data class SimpleAchievement(
    val imgId: Int,
    val title: String,
    val goal: String,
    val completed: Int
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(
    player: PlayerEntity,
    onNavigateToBack: () -> Unit
) {

    val currentLevel = player.currentLevel

    val simpleAchievements: List<SimpleAchievement> = listOf(
        SimpleAchievement(R.drawable.achievement_easy, "Fácil", "Completa 7 niveles", 7),
        SimpleAchievement(R.drawable.achievement_normal, "Normal", "Completa 18 niveles", 18),
        SimpleAchievement(R.drawable.achievement_hard, "Difícil", "Completa 24 niveles", 24)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = { Text(text = "Logros") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToBack() }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "back icon",
                            tint = Color.Black
                        )
                    }
                }
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
                items(simpleAchievements) { achievement ->
                    Achievement(
                        imgId = achievement.imgId,
                        title = achievement.title,
                        goal = achievement.goal,
                        completed = currentLevel > achievement.completed
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