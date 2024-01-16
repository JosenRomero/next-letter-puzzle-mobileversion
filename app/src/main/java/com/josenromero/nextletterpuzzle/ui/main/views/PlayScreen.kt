package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.josenromero.nextletterpuzzle.ui.components.AnimatedTransitionDialog
import com.josenromero.nextletterpuzzle.ui.components.ButtonsContainer
import com.josenromero.nextletterpuzzle.ui.components.Loading
import com.josenromero.nextletterpuzzle.ui.components.ResultContainer
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants
import com.josenromero.nextletterpuzzle.utils.checkWords

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayScreen(
    currentData: Item,
    player: PlayerEntity,
    isLoading: Boolean,
    onNavigateToAScreen: (route: String) -> Unit,
    nextLevelBtn: (player: PlayerEntity) -> Unit
) {

    val currentWord = remember { mutableStateOf("") }
    val words = remember { mutableStateListOf<String>() }
    var isOpenDialog by remember { mutableStateOf(false) }
    val arrResult = remember { mutableStateListOf<String>() }

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading) {
                    Loading()
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Level: ${player.currentLevel}")
                        Text(text = "Word: ${words.size}/${currentData.answer.size}")
                    }
                    Divider(modifier = Modifier.padding(0.dp, 15.dp))
                    Text(
                        text = currentData.topic,
                        textAlign = TextAlign.Center
                    )
                    Divider(modifier = Modifier.padding(0.dp, 15.dp))
                    Spacer(modifier = Modifier.height(50.dp))
                    ButtonsContainer(
                        letters = currentData.letters,
                        onClick = { letter ->
                            currentWord.value += letter
                        }
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(
                        text = currentWord.value,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                if (currentWord.value.isNotEmpty()) {
                                    currentWord.value =
                                        currentWord.value.substring(0, currentWord.value.length - 1)
                                }
                            },
                            enabled = currentWord.value.isNotEmpty()
                        ) {
                            Text(text = "x")
                        }
                        Button(
                            onClick = {
                                words.add(currentWord.value)
                                currentWord.value = ""
                                if (words.size == currentData.answer.size) {
                                    val res: List<String> = checkWords(currentData.answer, currentData.validAnswer, words)
                                    arrResult.addAll(res)
                                    isOpenDialog = true
                                }
                            },
                            enabled = currentWord.value.isNotEmpty()
                        ) {
                            if ((words.size + 1) < currentData.answer.size) {
                                Text(text = "Next Word")
                            } else {
                                Text(text = "Check Answer")
                            }
                        }
                    }
                }
                if (isOpenDialog) {
                    AnimatedTransitionDialog(onDismissRequest = { }) {
                        ResultContainer(
                            win = !arrResult.contains("x"),
                            arr = arrResult,
                            onNavigateToHomeScreen = { onNavigateToAScreen(AppScreens.HomeScreen.route) },
                            nextLevelBtn = { nextLevelBtn(player) },
                            tryAgainBtn = { onNavigateToAScreen(AppScreens.PlayScreen.route) }
                        )
                    }
                }
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
            currentData = Constants.dataFake[0],
            player = Constants.playerFake,
            isLoading = false,
            onNavigateToAScreen = {},
            nextLevelBtn = {}
        )
    }
}