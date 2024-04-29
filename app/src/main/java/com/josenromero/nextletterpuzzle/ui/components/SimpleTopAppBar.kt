package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: String,
    onNavigateToAScreen: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = {
            SimpleText(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = { onNavigateToAScreen() }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "back icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )

}