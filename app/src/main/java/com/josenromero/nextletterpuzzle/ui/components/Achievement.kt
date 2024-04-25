package com.josenromero.nextletterpuzzle.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun Achievement(
    @DrawableRes imgId: Int,
    title: String,
    goal: String,
    completed: Boolean
) {

    Card(
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = "Achievement $title",
                modifier = Modifier.size(100.dp)
            )
            SimpleText(
                text = title,
                modifier = Modifier.padding(0.dp, 20.dp),
                fontWeight = FontWeight.SemiBold
            )
            SimpleText(text = goal)
            if (completed) {
                SimpleText(
                    text = "Logro completado",
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Green)
                        .padding(5.dp)
                )
            } else {
                SimpleText(
                    text = "Logro bloqueado",
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Red)
                        .padding(5.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun AchievementPreview() {
    NextLetterPuzzleTheme {
        Achievement(
            imgId = R.drawable.achievement_easy,
            title = "Fácil",
            goal = "completa 10 niveles",
            completed = true
        )
    }
}