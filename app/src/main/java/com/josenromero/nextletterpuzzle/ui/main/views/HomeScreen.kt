package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.components.BottomSheet
import com.josenromero.nextletterpuzzle.ui.main.navigation.AppScreens
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToAScreen: (route: String) -> Unit,
    players: List<PlayerEntity>
) {

    var showBottomSheet by remember { mutableStateOf(false) }

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
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "logo",
                    modifier = Modifier.size(200.dp)
                )
                HorizontalDivider(
                    Modifier.height(30.dp),
                    color = Color.Transparent
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
                HorizontalDivider(
                    Modifier.height(30.dp),
                    color = Color.Transparent
                )
                if(players.isNotEmpty() && players[0].currentLevel <= Constants.lastLevel) {
                    OutlinedButton(
                        onClick = { onNavigateToAScreen(AppScreens.PlayScreen.route) }
                    ) {
                        Text(text = "Play")
                    }
                }
                HorizontalDivider(
                    Modifier.height(10.dp),
                    color = Color.Transparent
                )
                OutlinedButton(onClick = { showBottomSheet = true }) {
                    Text(text = "Instrucciones")
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