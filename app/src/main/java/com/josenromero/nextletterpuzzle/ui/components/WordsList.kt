package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun WordsList(
    words: List<String>
) {

    Column(
        modifier = Modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IndicatorItem(text = "Lista de palabras")
        HorizontalDivider(
            modifier = Modifier.height(30.dp),
            color = Color.Transparent
        )
        if (words.isNotEmpty()) {
            FlowColumn {
                words.forEach { word ->
                    SimpleText(
                        text = word,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }
        } else {
            SimpleText(text = "Lista vacía")
        }
    }

}

@Preview
@Composable
fun WordsListPreview() {
    NextLetterPuzzleTheme {
        WordsList(
            words = emptyList()
        )
    }
}