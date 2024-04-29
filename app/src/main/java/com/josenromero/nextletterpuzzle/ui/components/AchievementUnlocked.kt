package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AchievementUnlocked(
    text: String,
    player: PlayerEntity,
    achievementId: String,
    saveAchievement: (player: PlayerEntity, achievementId: String) -> Unit
) {

    val visible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            val isSaved = player.achievements.contains(achievementId)
            if(!isSaved) {
                saveAchievement(player, achievementId)
                delay(2000)
                visible.value = true
                delay(2000)
                visible.value = false
            }
        }
    }

    AnimatedVisibility(
        visible = visible.value,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + scaleIn(
            transformOrigin = TransformOrigin(0.5f, 0f)
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            SimpleText(
                text = text,
                modifier = Modifier.padding(20.dp),
                color = MaterialTheme.colorScheme.tertiary,
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
fun AchievementUnlockedPreview() {
    NextLetterPuzzleTheme {
        AchievementUnlocked(
            text = "Logro desbloqueado",
            player = Constants.playerFake,
            achievementId = "1",
            saveAchievement = { _, _ -> }
        )
    }
}