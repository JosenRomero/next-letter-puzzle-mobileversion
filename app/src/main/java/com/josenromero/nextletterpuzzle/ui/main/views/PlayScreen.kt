package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.AchievementUnlocked
import com.josenromero.nextletterpuzzle.ui.components.AnimatedTransitionDialog
import com.josenromero.nextletterpuzzle.ui.components.ButtonsContainer
import com.josenromero.nextletterpuzzle.ui.components.LinearIndicator
import com.josenromero.nextletterpuzzle.ui.components.Loading
import com.josenromero.nextletterpuzzle.ui.components.ResultContainer
import com.josenromero.nextletterpuzzle.ui.components.SimpleTopAppBar
import com.josenromero.nextletterpuzzle.ui.components.WordsList
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants
import com.josenromero.nextletterpuzzle.utils.checkAchievementUnlocked
import com.josenromero.nextletterpuzzle.utils.checkWords

@Composable
fun PlayScreen(
    data: List<Item>,
    player: PlayerEntity,
    isLoading: Boolean,
    lastLevel: Boolean,
    onNavigateToAScreen: (route: String) -> Unit,
    nextLevelBtn: (player: PlayerEntity) -> Unit,
    lastLevelCompleteBtn: (player: PlayerEntity) -> Unit,
    saveAchievement: (player: PlayerEntity, achievementId: String) -> Unit
) {

    var currentData: Item? = null

    if(player.currentLevel <= Constants.lastLevel) {
        currentData = data[player.currentLevel-1]
    }

    val currentWord = remember { mutableStateOf("") }
    val words = remember { mutableStateListOf<String>() }
    var isOpenDialog by remember { mutableStateOf(false) }
    var showWordsList by remember { mutableStateOf(false) }
    val arrResult = remember { mutableStateListOf<String>() }
    val currentProgressBar = remember { mutableStateOf(0) }

    val achievement = checkAchievementUnlocked(currentLevel = player.currentLevel)

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = "",
                onNavigateToAScreen = {
                    onNavigateToAScreen(AppScreens.HomeScreen.route)
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
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(currentData != null && !isLoading) {

                    if(achievement != null) {
                        AchievementUnlocked(
                            text = "Logro desbloqueado: ${achievement.title}",
                            player = player,
                            achievementId = achievement.id,
                            saveAchievement = saveAchievement
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Level: ${player.currentLevel}")
                        Text(text = "Word: ${words.size}/${currentData.answer.size}")
                    }
                    Text(
                        text = currentData.topic,
                        modifier = Modifier.padding(vertical = 50.dp),
                        textAlign = TextAlign.Center
                    )
                    LinearIndicator(number = currentProgressBar.value)
                    ButtonsContainer(
                        letters = currentData.letters,
                        onClick = { letter ->
                            currentWord.value += letter
                        }
                    )
                    Text(
                        text = currentWord.value,
                        modifier = Modifier.padding(bottom = 50.dp),
                        textAlign = TextAlign.Center
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (currentWord.value.isNotEmpty()) {
                                    currentWord.value =
                                        currentWord.value.substring(0, currentWord.value.length - 1)
                                }
                            },
                            enabled = currentWord.value.isNotEmpty(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(text = "Eliminar letra")
                        }
                        Button(
                            onClick = {
                                words.add(currentWord.value)
                                currentWord.value = ""
                                currentProgressBar.value = (100/currentData.answer.size) * words.size
                                if (words.size == currentData.answer.size) {
                                    val res: List<String> = checkWords(currentData.answer, currentData.validAnswer, words)
                                    arrResult.addAll(res)
                                    if(lastLevel && !arrResult.contains("x")) {
                                        lastLevelCompleteBtn(player)
                                    } else {
                                        isOpenDialog = true
                                    }
                                }
                            },
                            enabled = currentWord.value.isNotEmpty(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            if ((words.size + 1) < currentData.answer.size) {
                                Text(text = "Siguiente palabra")
                            } else {
                                Text(text = "Comprobar")
                            }
                        }
                    }
                    Button(
                        onClick = { showWordsList = !showWordsList },
                        modifier = Modifier.padding(top = 30.dp),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(text = if(showWordsList) "Ocultar lista de palabras" else "Ver lista de palabras" )
                    }
                } else {
                    Loading()
                }

                if (isOpenDialog) {
                    AnimatedTransitionDialog(onDismissRequest = { }) {
                        ResultContainer(
                            win = !arrResult.contains("x"),
                            arr = arrResult,
                            onNavigateToHomeScreen = {
                                isOpenDialog = false
                                if(!arrResult.contains("x")) {
                                    nextLevelBtn(player)
                                }
                                onNavigateToAScreen(AppScreens.HomeScreen.route)
                            },
                            nextLevelBtn = {
                                isOpenDialog = false
                                nextLevelBtn(player)
                                onNavigateToAScreen(AppScreens.PlayScreen.route)
                            },
                            tryAgainBtn = {
                                isOpenDialog = false
                                currentWord.value = ""
                                words.clear()
                                arrResult.clear()
                                currentProgressBar.value = 0
                            }
                        )
                    }
                }
                WordsList(
                    words = words,
                    visible = showWordsList
                )
            }
        }
    }

}

@Preview(name = "Light Mode", showSystemUi = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PlayScreenPreview() {
    NextLetterPuzzleTheme {
        PlayScreen(
            data = Constants.dataFake,
            player = Constants.playerFake,
            isLoading = false,
            lastLevel = false,
            onNavigateToAScreen = {},
            nextLevelBtn = {},
            lastLevelCompleteBtn = {},
            saveAchievement = { _, _ ->  }
        )
    }
}