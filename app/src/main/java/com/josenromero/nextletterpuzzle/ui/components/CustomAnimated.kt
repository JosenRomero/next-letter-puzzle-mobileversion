package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
fun AnimatedFadeAndExpand(
    visible: Boolean = true,
    content:  @Composable AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandIn(),
        exit = shrinkOut() + fadeOut(),
        content = content
    )

}

@Composable
fun AnimatedSlideVertically(
    visible: Boolean = true,
    content:  @Composable AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(tween(durationMillis = 300)),
        exit = slideOutVertically(tween(durationMillis = 300)),
        content = content
    )

}