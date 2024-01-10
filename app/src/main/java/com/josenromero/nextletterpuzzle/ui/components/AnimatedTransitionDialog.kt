package com.josenromero.nextletterpuzzle.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            Box {
                Spacer(modifier = Modifier.height(130.dp))
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(25.dp, 10.dp, 25.dp, 10.dp)
                        )
                ) {
                    content()
                }
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