package com.example.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.weather.model.CurrentWeather
import com.example.presentation_core.ext.toDateTime
import com.example.presentation_core.theme.WeatherForecastTheme
import com.example.ui.weather.common.SolarTimeInfoWidget
import com.example.ui.weather.common.TemperatureInfo
import java.time.ZoneId

@Composable
fun CurrentWeatherWidget(
    modifier: Modifier = Modifier,
    currentWeather: CurrentWeather,
    temperatureUnit: String,
    timeZone: ZoneId,
) {
    Column(modifier = modifier) {
        CurrentTimeInfo(timeStamp = currentWeather.timeStamp, timeZone = timeZone)
        SunriseSunsetRow(
            modifier = Modifier.fillMaxWidth(),
            sunsetTimeStamp = currentWeather.sunsetTimeStamp,
            sunriseTimeStamp = currentWeather.sunriseTimeStamp,
            timeZone = timeZone,
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
    timeZone: ZoneId,
) {
    Row(
        modifier = modifier.height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SolarTimeInfoWidget(
            modifier = Modifier.weight(1f),
            timeStamp = sunriseTimeStamp,
            timeZone = timeZone,
            icon = painterResource(R.drawable.ic_sunrise)
        )
        SolarTimeInfoWidget(
            modifier = Modifier.weight(1f),
            timeStamp = sunsetTimeStamp,
            timeZone = timeZone,
            icon = painterResource(R.drawable.ic_sunset)
        )
    }
}

@Composable
private fun CurrentTimeInfo(
    modifier: Modifier = Modifier,
    timeStamp: Int,
    timeZone: ZoneId,
) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = timeStamp.toDateTime(timeZone),
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
                weatherIconUrls = emptyList(),
            ),
            temperatureUnit = "°C",
            timeZone = ZoneId.systemDefault(),
        )
    }
}