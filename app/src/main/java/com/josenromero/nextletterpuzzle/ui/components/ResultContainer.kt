package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun ResultContainer(
    win: Boolean,
    arr: List<String>,
    onNavigateToHomeScreen: () -> Unit,
    nextLevelBtn: () -> Unit,
    tryAgainBtn: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if(win) "Level completed!" else "Lost!",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            LazyRow() {
                items(arr) {item ->
                    Icon(
                        painter =
                        if (item == "o") painterResource(id = R.drawable.circle_check)
                        else painterResource(id = R.drawable.circle_x),
                        contentDescription = "result icon",
                        modifier = Modifier.size(24.dp),
                        tint = if (item == "o") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onNavigateToHomeScreen() }) {
                Text(text = "Menu")
            }
            if (win) {
                Button(onClick = { nextLevelBtn() }) {
                    Text(text = "Next Level")
                }
            } else {
                Button(onClick = { tryAgainBtn() }) {
                    Text(text = "Try Again")
                }
            }
        }

    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun WinContainerPreview() {
    NextLetterPuzzleTheme {
        ResultContainer(
            win = true,
            arr = arrayListOf("o", "x", "x"),
            onNavigateToHomeScreen = {},
            nextLevelBtn = {},
            tryAgainBtn = {}
        )
    }
}