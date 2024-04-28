package com.josenromero.nextletterpuzzle.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    SimpleCard(
        defaultElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imgId),
                contentDescription = "Achievement $title",
                modifier = Modifier.size(150.dp)
            )
            Column {
                SimpleText(
                    text = title,
                    modifier = Modifier.padding(bottom = 20.dp),
                    fontWeight = FontWeight.SemiBold
                )
                SimpleText(
                    text = goal,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                if (completed) {
                    SimpleText(
                        text = "Logro completado",
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(5.dp)
                    )
                } else {
                    SimpleText(
                        text = "Logro bloqueado",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.errorContainer,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(5.dp)
                    )
                }
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