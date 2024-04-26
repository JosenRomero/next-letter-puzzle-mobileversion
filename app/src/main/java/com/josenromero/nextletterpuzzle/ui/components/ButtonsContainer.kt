package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ButtonsContainer(
    letters: ArrayList<String>,
    onClick: (letter: String) -> Unit
) {

    FlowRow(
        modifier = Modifier.padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        letters.forEach { letter ->
            Button(
                onClick = { onClick(letter) },
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                shape = ShapeDefaults.Small
            ) {
                SimpleText(text = letter)
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ButtonsContainerPreview() {
    NextLetterPuzzleTheme {
        ButtonsContainer(
            letters = Constants.dataFake[0].letters,
            onClick = {}
        )
    }
}