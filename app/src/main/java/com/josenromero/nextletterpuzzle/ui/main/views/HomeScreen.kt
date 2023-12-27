package com.josenromero.nextletterpuzzle.ui.main.views

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.josenromero.nextletterpuzzle.data.Item
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun HomeScreen(
    players: List<PlayerEntity>,
    data: List<Item>
) {

    Text(text = "HomeScreen")

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    NextLetterPuzzleTheme {
        HomeScreen(
            players = emptyList(),
            data = emptyList()
        )
    }
}