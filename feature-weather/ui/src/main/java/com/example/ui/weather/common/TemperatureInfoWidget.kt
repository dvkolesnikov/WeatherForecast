package com.example.ui.weather.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.R
import com.example.ui.weather.ext.formatTemperature

@Composable
internal fun TemperatureInfo(
    modifier: Modifier = Modifier,
    temperature: Float,
    temperatureFeeling: Float,
    units: String,
    format: TemperatureInfoFormat = TemperatureInfoFormat.FULL,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(
                R.string.weather_label_temperature,
                temperature.formatTemperature(units)
            ),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            fontSize = when (format) {
                TemperatureInfoFormat.FULL -> 32.sp
                TemperatureInfoFormat.COMPACT -> 16.sp
            },
            fontWeight = FontWeight.Bold,
        )
        if (format != TemperatureInfoFormat.COMPACT) {
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(
            text = when (format) {
                TemperatureInfoFormat.FULL -> {
                    stringResource(
                        R.string.weather_label_temperature_feels_like,
                        temperatureFeeling.formatTemperature(units),
                    )
                }

                TemperatureInfoFormat.COMPACT -> {
                    stringResource(
                        R.string.weather_label_temperature,
                        temperatureFeeling.formatTemperature(units),
                    )
                }
            },
            textAlign = TextAlign.Center,
            style = when (format) {
                TemperatureInfoFormat.FULL -> MaterialTheme.typography.bodyMedium
                TemperatureInfoFormat.COMPACT -> MaterialTheme.typography.bodySmall
            },
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

internal enum class TemperatureInfoFormat {
    FULL,
    COMPACT,
}