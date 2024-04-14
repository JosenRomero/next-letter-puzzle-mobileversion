package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun LinearIndicator(
    number: Int
) {

    val currentProgress = (number).toFloat() / 100

    val animationProgress by animateFloatAsState(
        targetValue = currentProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "animation progress"
    )

    LinearProgressIndicator(
        progress = { animationProgress },
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
        strokeCap = StrokeCap.Round
    )

}

@Preview
@Composable
fun LinearIndicatorPreview() {
    NextLetterPuzzleTheme {
        LinearIndicator(
            number = 24
        )
    }
}