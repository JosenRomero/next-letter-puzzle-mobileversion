package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SimpleText(
                text = "Cómo jugar",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))
            SimpleText(text = "En cada nivel se te proporcionará un conjunto de letras y un tema.")
            SimpleText(text = "Tu objetivo es encontrar todas las palabras relacionadas con el tema utilizando las letras proporcionadas.")
            Spacer(modifier = Modifier.height(24.dp))

            SimpleText(text = "1. Haz clic en las letras para formar una palabra relacionada con el tema.")
            SimpleText(text = "2. Luego, presiona el botón 'Siguiente palabra' para guardar la palabra.")
            SimpleText(text = "3. Repite los pasos 1 y 2 para encontrar todas las palabras posibles en el nivel.")
            SimpleText(text = "4. Luego, presiona el botón 'Comprobar' para verificar tus respuestas.")

            ResultIndicatorsContainer()

            Button(onClick = closeBtn) {
                SimpleText(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Entendido",
                    textAlign = TextAlign.Center
                )
            }
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