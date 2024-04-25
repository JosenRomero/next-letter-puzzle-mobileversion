package com.josenromero.nextletterpuzzle.ui.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R
import com.josenromero.nextletterpuzzle.ui.components.SimpleText
import com.josenromero.nextletterpuzzle.ui.components.SimpleTopAppBar

@Composable
fun AboutScreen(
    onNavigateToBack: () -> Unit
) {

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = "Acerca de",
                onNavigateToAScreen = {
                    onNavigateToBack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
            SimpleText(
                text = "Next Letter Puzzle",
                modifier = Modifier.padding(vertical = 30.dp),
                fontWeight = FontWeight.SemiBold
            )
            SimpleText(text = "Desarrollado por José Romero")
        }
    }

}