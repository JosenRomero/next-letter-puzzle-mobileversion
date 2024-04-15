package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import com.josenromero.nextletterpuzzle.utils.Constants

@Composable
fun ButtonsContainer(
    letters: ArrayList<String>,
    onClick: (letter: String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 70.dp),
        modifier = Modifier.padding(vertical = 50.dp)
    ) {
        itemsIndexed(letters) {_, letter ->
            OutlinedButton(
                onClick = { onClick(letter) },
                modifier = Modifier.padding(5.dp),
                shape = ShapeDefaults.Small
            ) {
                Text(text = letter)
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