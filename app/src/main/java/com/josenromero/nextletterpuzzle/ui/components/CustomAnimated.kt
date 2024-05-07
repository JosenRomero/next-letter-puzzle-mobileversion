package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.launch

@Composable
fun CustomAnimated(
    enter: EnterTransition,
    exit: ExitTransition,
    content:  @Composable AnimatedVisibilityScope.() -> Unit
) {

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            visible = true
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = enter,
        exit = exit,
        content = content
    )

}

@Composable
fun AnimatedFadeAndExpand(
    content:  @Composable AnimatedVisibilityScope.() -> Unit
) {

    CustomAnimated(
        enter = fadeIn() + expandIn(expandFrom = Alignment.Center),
        exit = shrinkOut() + fadeOut(),
        content = content
    )

}

@Composable
fun AnimatedSlideVertically(
    content:  @Composable AnimatedVisibilityScope.() -> Unit
) {

    CustomAnimated(
        enter = slideInVertically(tween(durationMillis = 700)),
        exit = slideOutVertically(tween(durationMillis = 700)),
        content = content
    )

}