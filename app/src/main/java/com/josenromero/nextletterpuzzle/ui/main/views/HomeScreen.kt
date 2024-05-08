package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.AnimatedFadeAndExpand
import com.josenromero.nextletterpuzzle.ui.components.BottomSheet
import com.josenromero.nextletterpuzzle.ui.components.CustomBottomAppBar
import com.josenromero.nextletterpuzzle.ui.components.CustomIconButton
import com.josenromero.nextletterpuzzle.ui.components.SimpleText
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

@Composable
fun HomeScreen(
    onNavigateToAScreen: (route: String) -> Unit,
    players: List<PlayerEntity>,
    showHowToPlay: Boolean?,
    updateHowToPlay: (value: Boolean) -> Unit
) {

    var showBottomSheet by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val logoAnim by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logo animation"
    )

    // first game
    if(showHowToPlay == false) {
        showBottomSheet = true
        updateHowToPlay(true)
    }

    Scaffold(
        bottomBar = {
            CustomBottomAppBar {
                CustomIconButton(
                    onClick = { showBottomSheet = true },
                    icon = painterResource(id = R.drawable.question_mark_icon),
                    contentDescription = "howToPlay icon"
                )
                CustomIconButton(
                    onClick = { onNavigateToAScreen(AppScreens.AchievementsScreen.route) },
                    modifier = Modifier.padding(horizontal = 10.dp),
                    icon = painterResource(id = R.drawable.achievement_icon),
                    contentDescription = "achievements icon"
                )
                CustomIconButton(
                    onClick = { onNavigateToAScreen(AppScreens.AboutScreen.route) },
                    icon = painterResource(id = R.drawable.info_icon),
                    contentDescription = "about icon"
                )
            }
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
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .graphicsLayer {
                            scaleX = logoAnim
                            scaleY = logoAnim
                            transformOrigin = TransformOrigin.Center
                        }
                )
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.title_s1),
                        contentDescription = "title part1",
                        modifier = Modifier.size(100.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.title_s2),
                        contentDescription = "title part2",
                        modifier = Modifier.size(100.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.title_s3),
                        contentDescription = "title part3",
                        modifier = Modifier.size(100.dp)
                    )
                }
                if(players.isNotEmpty()) {
                    AnimatedFadeAndExpand {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(vertical = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                SimpleText(
                                    text = "${players[0].achievements.size}",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                                SimpleText(
                                    text = "Logros",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                SimpleText(
                                    text = if(players[0].currentLevel > Constants.lastLevel) "${Constants.lastLevel}" else "${players[0].currentLevel}",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                                SimpleText(
                                    text = "Nivel",
                                    modifier = Modifier.fillMaxWidth(),
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    AnimatedFadeAndExpand {
                        Button(
                            onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) },
                            shape = MaterialTheme.shapes.small,
                            enabled = players[0].currentLevel <= Constants.lastLevel
                        ) {
                            SimpleText(text = "Jugar")
                        }
                    }
                }
                if(showBottomSheet) {
                    BottomSheet(
                        onDismiss = { showBottomSheet = false },
                        closeBtn = { showBottomSheet = false }
                    )
                }
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    NextLetterPuzzleTheme {
        HomeScreen(
            onNavigateToAScreen = {},
            players = emptyList(),
            showHowToPlay = false,
            updateHowToPlay = {}
        )
    }
}