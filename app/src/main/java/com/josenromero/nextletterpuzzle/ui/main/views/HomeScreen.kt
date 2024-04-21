package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.BottomSheet
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

@Composable
fun HomeScreen(
    onNavigateToAScreen: (route: String) -> Unit,
    players: List<PlayerEntity>
) {

    var showBottomSheet by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val dy by infiniteTransition.animateFloat(
        initialValue = -100f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logo animation"
    )

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .graphicsLayer(
                            translationY = dy
                        )
                )
                Row(
                    modifier = Modifier.padding(bottom = 30.dp)
                ) {
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
                if(players.isNotEmpty() && players[0].currentLevel <= Constants.lastLevel) {
                    Button(
                        onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) },
                        shape = MaterialTheme.shapes.small
                    ) {
                        Text(text = "Play")
                    }
                }
                OutlinedButton(
                    onClick = { onNavigateToAScreen(AppScreens.AchievementsScreen.route) },
                    modifier = Modifier.padding(vertical = 10.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Logros")
                }
                OutlinedButton(
                    onClick = { showBottomSheet = true },
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "Instrucciones")
                }
                OutlinedButton(
                    onClick = { onNavigateToAScreen(AppScreens.AboutScreen.route) },
                    modifier = Modifier.padding(top = 10.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = "About")
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
            players = emptyList()
        )
    }
}