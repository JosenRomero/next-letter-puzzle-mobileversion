package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAScreen: (route: String) -> Unit,
    players: List<PlayerEntity>
) {

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
                OutlinedButton(
                    onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) }
                ) {
                    Text(text = "Play")
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