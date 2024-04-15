package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@Composable
fun WordsList(
    words: List<String>,
    visible: Boolean
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(700)
        ),
        exit = fadeOut(
            animationSpec = tween(700)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            Text(
                text = "Lista de Palabras",
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            if (words.isNotEmpty()) {
                LazyColumn() {
                    items(words) { word ->
                        Text(text = word)
                    }
                }
            } else {
                Text(
                    text = "Lista vacia",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@Preview
@Composable
fun WordsListPreview() {
    NextLetterPuzzleTheme {
        WordsList(
            words = emptyList(),
            visible = true
        )
    }
}