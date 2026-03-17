package com.example.ui.weather.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.ui.weather.R
import kotlin.math.roundToInt

@Composable
fun PrecipitationInfoWidget(
    modifier: Modifier = Modifier,
    probability: Float,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_precipitation),
            contentDescription = "Precipitation icon",
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${(probability * 100).roundToInt()}%"
        )
    }
}