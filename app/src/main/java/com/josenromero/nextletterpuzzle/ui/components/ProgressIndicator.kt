package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun ProgressIndicator(
    number: Int,
    totalWords: Int,
    currentWords: Int
) {

    val currentProgress = (number).toFloat() / 100

    val animationProgress by animateFloatAsState(
        targetValue = currentProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "animation progress"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            SimpleText(
                text = "Palabras totales: $totalWords",
                modifier = Modifier.padding(bottom = 10.dp)
            )
            SimpleText(text = "Palabras agregadas: $currentWords")
        }
        Column {
            CircularProgressIndicator(
                progress = { animationProgress },
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }

}

@Preview
@Composable
fun ProgressIndicatorPreview() {
    NextLetterPuzzleTheme {
        ProgressIndicator(
            number = 24,
            totalWords = 5,
            currentWords = 0
        )
    }
}