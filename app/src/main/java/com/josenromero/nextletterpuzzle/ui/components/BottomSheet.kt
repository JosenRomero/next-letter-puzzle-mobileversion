package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    closeBtn: () -> Unit
) {

    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Cómo jugar",
                textAlign = TextAlign.Center
            )
            HorizontalDivider(
                Modifier.height(10.dp),
                color = Color.Transparent
            )
            Text(text = "En cada nivel se te proporcionará un conjunto de letras y un tema.")
            Text(text = "Tu objetivo es encontrar todas las palabras relacionadas con el tema utilizando las letras proporcionadas.")
            HorizontalDivider(
                Modifier.height(20.dp),
                color = Color.Transparent
            )
            Text(text = "1. Haz clic en las letras para formar una palabra relacionada con el tema.")
            Text(text = "2. Luego, presiona el botón 'Siguiente palabra' para guardar la palabra.")
            Text(text = "3. Repite los pasos 1 y 2 para encontrar todas las palabras posibles en el nivel.")
            Text(text = "4. Luego, presiona el botón 'Comprobar' para verificar tus respuestas.")
            HorizontalDivider(
                Modifier.height(20.dp),
                color = Color.Transparent
            )
            Button(onClick = closeBtn) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Aceptar",
                    textAlign = TextAlign.Center
                )
            }
            HorizontalDivider(
                Modifier.height(20.dp),
                color = Color.Transparent
            )
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomSheetPreview() {
    NextLetterPuzzleTheme {
        BottomSheet(
            onDismiss = {},
            closeBtn = {}
        )
    }
}