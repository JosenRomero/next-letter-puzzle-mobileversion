package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.josenromero.nextletterpuzzle.ui.theme.NextLetterPuzzleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun AnimatedScaleInTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            animationSpec = tween(700)
        ),
        exit = scaleOut(
            animationSpec = tween(700)
        ),
        content = content
    )

}

@Composable
fun AnimatedTransitionDialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {

    val animateTrigger = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            delay(500)
            animateTrigger.value = true
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        AnimatedScaleInTransition(visible = animateTrigger.value) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }

}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AnimatedTransitionDialogPreview() {
    NextLetterPuzzleTheme {
        AnimatedTransitionDialog(
            onDismissRequest = {},
            content = {}
        )
    }
}