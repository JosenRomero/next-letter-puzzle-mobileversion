package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R

data class ResultIndicator (
    val id: Int = R.drawable.circle_check,
    val color: Color,
    val text: String
)

@Composable
fun ResultIndicatorsContainer() {

    val resultIndicators: List<ResultIndicator> = listOf(
        ResultIndicator(color = MaterialTheme.colorScheme.primary, text = "Respuesta  correcta."),
        ResultIndicator(color = MaterialTheme.colorScheme.primaryContainer, text = "Respuesta  correcta y secreta."),
        ResultIndicator(id = R.drawable.circle_x, color = MaterialTheme.colorScheme.error, text = "Respuesta  incorrecta.")
    )

    Column(
        modifier = Modifier.padding(vertical = 30.dp)
    ) {

        SimpleText(
            text = "Indicadores de resultado en cada nivel",
            modifier = Modifier.padding(bottom = 15.dp)
        )

        resultIndicators.forEach { resultIndicator ->
            Row(
                modifier = Modifier.padding(start = 10.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = resultIndicator.id),
                    contentDescription = "result indicator icon",
                    modifier = Modifier.size(24.dp),
                    tint = resultIndicator.color
                )
                SimpleText(
                    text = resultIndicator.text,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
        }
    }


}