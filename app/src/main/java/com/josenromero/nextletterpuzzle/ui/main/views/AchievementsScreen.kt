package com.josenromero.nextletterpuzzle.ui.main.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(
    player: PlayerEntity,
    onNavigateToBack: () -> Unit
) {

    val currentLevel = player.currentLevel

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Achievement(
                    imgId = R.drawable.achievement_easy,
                    title = "Fácil",
                    goal = "Completa 7 niveles",
                    completed = currentLevel > 7
                )
                Achievement(
                    imgId = R.drawable.achievement_normal,
                    title = "Normal",
                    goal = "Completa 18 niveles",
                    completed = currentLevel > 18
                )
                Achievement(
                    imgId = R.drawable.achievement_hard,
                    title = "Difícil",
                    goal = "Completa 24 niveles",
                    completed = currentLevel > 24
                )
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