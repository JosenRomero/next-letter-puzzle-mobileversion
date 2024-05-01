package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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

    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SimpleText(
                text = "Palabras",
                modifier = Modifier.weight(1f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start
            )
            SimpleText(
                text = "${currentWords}/${totalWords}",
                modifier = Modifier.weight(1f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End
            )
        }
        LinearProgressIndicator(
            progress = { animationProgress },
            modifier = Modifier.fillMaxWidth(),
            strokeCap = StrokeCap.Round
        )
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