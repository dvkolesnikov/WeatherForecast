package com.example.ui.weather.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun WeatherConditionIcons(
    modifier: Modifier = Modifier,
    urls: List<String>,
) {
    Row(modifier = modifier) {
        urls.forEach {
            WeatherConditionIcon(url = it)
        }
    }
}

@Composable
fun WeatherConditionIcon(
    modifier: Modifier = Modifier,
    url: String,
) {
    AsyncImage(
        modifier = modifier.size(32.dp),
        model = url,
        contentDescription = null,
    )
}