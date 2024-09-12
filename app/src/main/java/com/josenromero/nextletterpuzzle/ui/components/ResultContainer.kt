package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Answer

@Composable
fun ResultContainer(
    win: Boolean,
    arr: List<String>,
    onNavigateToHomeScreen: () -> Unit,
    nextLevelBtn: () -> Unit,
    tryAgainBtn: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(25.dp, 10.dp, 0.dp, 0.dp)
                )
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SimpleText(
                text = if(win) "Nivel completado!" else "Perdiste!",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(0.dp, 0.dp, 25.dp, 10.dp)
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                LazyRow() {
                    items(arr) {item ->
                        Icon(
                            painter =
                            if (item !== Answer.Wrong.character) painterResource(id = R.drawable.circle_check)
                            else painterResource(id = R.drawable.circle_x),
                            contentDescription = "result icon",
                            modifier = Modifier.size(24.dp),
                            tint = if (item !== Answer.Wrong.character) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { onNavigateToHomeScreen() }) {
                    SimpleText(text = "Menu")
                }
                if (win) {
                    Button(onClick = { nextLevelBtn() }) {
                        SimpleText(text = "Siguiente nivel")
                    }
                } else {
                    Button(onClick = { tryAgainBtn() }) {
                        SimpleText(text = "Intentar otra vez")
                    }
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