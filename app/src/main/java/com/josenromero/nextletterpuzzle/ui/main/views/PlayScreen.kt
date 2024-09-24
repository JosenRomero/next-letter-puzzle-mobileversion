package com.josenromero.nextletterpuzzle.ui.main.views

import android.app.Activity
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.AchievementUnlocked
import com.josenromero.nextletterpuzzle.ui.components.AnimatedTransitionDialog
import com.josenromero.nextletterpuzzle.ui.components.ButtonsContainer
import com.josenromero.nextletterpuzzle.ui.components.IndicatorItem
import com.josenromero.nextletterpuzzle.ui.components.ProgressIndicator
import com.josenromero.nextletterpuzzle.ui.components.Loading
import com.josenromero.nextletterpuzzle.ui.components.ResultContainer
import com.josenromero.nextletterpuzzle.ui.components.SimpleText
import com.josenromero.nextletterpuzzle.ui.components.SimpleTopAppBar
import com.josenromero.nextletterpuzzle.ui.components.WordsList
import com.josenromero.nextletterpuzzle.ui.main.Ads
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Answer
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

    val currentActivity = LocalContext.current as Activity
    var currentData: Item? = null
    val currentWord = remember { mutableStateOf("") }
    val words = remember { mutableStateListOf<String>() }
    var isOpenDialog by remember { mutableStateOf(false) }
    val arrResult = remember { mutableStateListOf<String>() }
    val initialProgressBar = 10
    val currentProgressBar = remember { mutableStateOf(initialProgressBar) }
    var endGame by remember { mutableStateOf(false) }

    val currentLevel = if(!endGame) player.currentLevel else Constants.lastLevel

    val achievement = checkAchievementUnlocked(currentLevel = currentLevel)

    if(currentLevel <= Constants.lastLevel) {
        currentData = data[currentLevel-1]
    }

    if (currentData != null && !isLoading) {
        Scaffold(
            topBar = {
                SimpleTopAppBar(
                    title = "Nivel $currentLevel",
                    onNavigateToAScreen = {
                        onNavigateToAScreen(AppScreens.HomeScreen.route)
                    }
                )
            },
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 30.dp, end = 16.dp, bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (achievement != null && Constants.achievementsIDs_basic.contains(achievement.id)) {
                        ShowAchievement(player, achievement.title, achievement.id, saveAchievement)
                    }
                    ProgressIndicator(
                        number = currentProgressBar.value,
                        totalWords = currentData.answer.size,
                        currentWords = words.size
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IndicatorItem(text = "Tema")
                        SimpleText(
                            text = currentData.topic,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                    SimpleText(
                        text = currentWord.value,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    ButtonsContainer(
                        letters = currentData.letters,
                        onClick = { letter ->
                            currentWord.value += letter
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                if (currentWord.value.isNotEmpty()) {
                                    currentWord.value =
                                        currentWord.value.substring(
                                            0,
                                            currentWord.value.length - 1
                                        )
                                }
                            },
                            enabled = currentWord.value.isNotEmpty(),
                            shape = MaterialTheme.shapes.small,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                                contentColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            SimpleText(text = "Eliminar letra")
                        }
                        Button(
                            onClick = {
                                words.add(currentWord.value)
                                currentWord.value = ""
                                currentProgressBar.value =
                                    (100 / currentData.answer.size) * words.size
                                if (words.size == currentData.answer.size) {
                                    val res: List<String> = checkWords(
                                        currentData.answer,
                                        currentData.validAnswer,
                                        words
                                    )
                                    arrResult.addAll(res)
                                    if (lastLevel && !arrResult.contains(Answer.Wrong.character)) {
                                        endGame = true
                                        lastLevelCompleteBtn(player)
                                    } else {
                                        isOpenDialog = true
                                        Ads.displayInterstitialAd(currentActivity)
                                    }
                                }
                            },
                            enabled = currentWord.value.isNotEmpty(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            if ((words.size + 1) < currentData.answer.size) {
                                SimpleText(text = "Siguiente palabra")
                            } else {
                                SimpleText(text = "Comprobar")
                            }
                        }
                    }
                    WordsList(
                        words = words
                    )
                }
                if (isOpenDialog) {
                    AnimatedTransitionDialog(onDismissRequest = { }) {
                        if (achievement != null && !arrResult.contains(Answer.Wrong.character) && !arrResult.contains(Answer.Right.character)) {
                            ShowAchievement(
                                player = player,
                                achievementTitle = achievement.title,
                                achievementID = achievement.id,
                                saveAchievement = saveAchievement
                            )
                        }
                        Spacer(modifier = Modifier.height(50.dp))
                        ResultContainer(
                            isLevelWithSecrets = Constants.levels_with_secrets.contains(currentLevel),
                            win = !arrResult.contains(Answer.Wrong.character),
                            arr = arrResult,
                            onNavigateToHomeScreen = {
                                isOpenDialog = false
                                if (!arrResult.contains(Answer.Wrong.character)) {
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
                                currentProgressBar.value = initialProgressBar
                            }
                        )
                    }
                }
            }
        }
    } else {
        Loading(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        )
    }

}

@Composable
fun ShowAchievement(
    player: PlayerEntity,
    achievementTitle:  String,
    achievementID: String,
    saveAchievement: (player: PlayerEntity, achievementId: String) -> Unit
) {

    AchievementUnlocked(
        text = "Logro desbloqueado: $achievementTitle",
        player = player,
        achievementId = achievementID,
        saveAchievement = saveAchievement
    )

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