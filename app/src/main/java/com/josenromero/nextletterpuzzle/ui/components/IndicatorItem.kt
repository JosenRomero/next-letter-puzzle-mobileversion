package com.josenromero.nextletterpuzzle.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.josenromero.nextletterpuzzle.R

@Composable
fun IndicatorItem(
    text: String
) {

    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 5.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.circle_icon),
            contentDescription = "Indicator icon",
            modifier = Modifier.size(10.dp),
            tint = MaterialTheme.colorScheme.tertiary
        )
        SimpleText(
            text = text,
            modifier = Modifier.padding(start = 5.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}