package com.example.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.CurrentWeather
import com.example.presentation_core.ext.toDateTime
import com.example.presentation_core.ext.toTimeHHmm
import com.example.presentation_core.theme.WeatherForecastTheme
import com.example.ui.R
import com.example.ui.weather.common.TemperatureInfo

@Composable
fun CurrentWeatherWidget(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
    temperatureUnit: String,
) {
    Column(modifier = modifier) {
        CurrentTimeInfo(timeStamp = currentWeather.timeStamp)
        SunriseSunsetRow(
            modifier = Modifier.fillMaxWidth(),
            sunsetTimeStamp = currentWeather.sunsetTimeStamp,
            sunriseTimeStamp = currentWeather.sunriseTimeStamp,
        )
        TemperatureInfo(
            modifier = Modifier.fillMaxWidth(),
            temperature = currentWeather.temperature,
            temperatureFeeling = currentWeather.temperatureFeelsLike,
            units = temperatureUnit,
        )
    }
}

@Composable
private fun SunriseSunsetRow(
    modifier: Modifier = Modifier,
    sunsetTimeStamp: Int,
    sunriseTimeStamp: Int,
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SolarTimeInfo(
            modifier = Modifier.weight(1f),
            timeStamp = sunriseTimeStamp,
            icon = painterResource(R.drawable.ic_sunrise)
        )
        SolarTimeInfo(
            modifier = Modifier.weight(1f),
            timeStamp = sunsetTimeStamp,
            icon = painterResource(R.drawable.ic_sunset)
        )
    }
}

@Composable
private fun SolarTimeInfo(
    modifier: Modifier = Modifier,
    timeStamp: Int,
    icon: Painter,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = icon,
            contentDescription = "Solar time icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = timeStamp.toTimeHHmm(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
        )
    }

}

@Composable
private fun CurrentTimeInfo(
    modifier: Modifier = Modifier,
    timeStamp: Int,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = timeStamp.toDateTime(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )

}

@Composable
@Preview
private fun CurrentWeatherWidgetPreview() {
    WeatherForecastTheme {
        CurrentWeatherWidget(
            currentWeather = CurrentWeather(
                timeStamp = (System.currentTimeMillis() / 1000).toInt(),
                temperature = 15.5f,
                sunsetTimeStamp = (System.currentTimeMillis() / 1000).toInt(),
                sunriseTimeStamp = (System.currentTimeMillis() / 1000).toInt(),
                temperatureFeelsLike = 13.3f,
                pressure = 750,
                humidity = 45,
                windSpeed = 3.4f,
            ),
            temperatureUnit = "°C",
        )
    }
}