package com.josenromero.nextletterpuzzle.ui.main.views

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun EndScreen(
    onNavigateToHomeScreen: () -> Unit
) {
    
    val infiniteTransition = rememberInfiniteTransition(label = "")
    
    val textAnim by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "text animation"
    )

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
                Text(
                    text = "Congratulations!",
                    modifier = Modifier.graphicsLayer {
                        scaleX = textAnim
                        scaleY = textAnim
                        transformOrigin = TransformOrigin.Center
                    },
                    textAlign = TextAlign.Center,
                    style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
                )
                Text(
                    text = "You have completed all levels",
                    modifier = Modifier.fillMaxWidth().padding(top = 50.dp, bottom = 20.dp),
                    textAlign = TextAlign.Center
                )
                Button(onClick = { onNavigateToHomeScreen() }) {
                    Text(text = "Menu")
                }
            }
        }
    }

}

@Preview
@Composable
fun EndScreenPreview() {
    NextLetterPuzzleTheme {
        EndScreen(
            onNavigateToHomeScreen = {}
        )
    }
}