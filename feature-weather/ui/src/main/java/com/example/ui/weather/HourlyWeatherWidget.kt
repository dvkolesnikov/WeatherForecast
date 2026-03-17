package com.example.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.model.HourlyWeather
import com.example.presentation_core.ext.toTimeHHmm
import com.example.presentation_core.theme.WeatherForecastTheme
import com.example.ui.weather.common.PrecipitationInfoWidget
import com.example.ui.weather.common.TemperatureInfo
import com.example.ui.weather.common.TemperatureInfoFormat
import com.example.ui.weather.common.WeatherConditionIcons

@Composable
fun HourlyWeatherWidget(
    modifier: Modifier = Modifier,
    hourlyWeatherItems: List<HourlyWeather>,
    tempUnit: String,
) {
    LazyRow(
        modifier = modifier,
    ) {
        items(hourlyWeatherItems) { item ->
            HourlyWeatherItem(hourlyWeather = item, tempUnit = tempUnit)
        }
    }

}

@Composable
private fun HourlyWeatherItem(
    modifier: Modifier = Modifier,
    hourlyWeather: HourlyWeather,
    tempUnit: String,
) {
    Card(modifier = modifier.padding(8.dp)) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = hourlyWeather.timeStamp.toTimeHHmm())
            TemperatureInfo(
                temperature = hourlyWeather.temperature,
                temperatureFeeling = hourlyWeather.temperatureFeelsLike,
                units = tempUnit,
                format = TemperatureInfoFormat.COMPACT,
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrecipitationInfoWidget(
                probability = hourlyWeather.precipitationProbability,
            )
            WeatherConditionIcons(urls = hourlyWeather.weatherIconUrls)
        }
    }
}

@Composable
@Preview
private fun HourlyWeatherWidgetPreview() {
    WeatherForecastTheme {
        HourlyWeatherWidget(
            hourlyWeatherItems = listOf(
                previewHourlyItem,
                previewHourlyItem.copy(temperature = -14.4f),
                previewHourlyItem.copy(temperatureFeelsLike = 11.1f)
            ),
            tempUnit = "°C"
        )
    }
}

private val previewHourlyItem by lazy {
    HourlyWeather(
        timeStamp = (System.currentTimeMillis() / 1000).toInt(),
        temperature = 19.5f,
        temperatureFeelsLike = 17.9f,
        pressure = 1010,
        humidity = 45,
        dewPointTemperature = 14f,
        uvIndex = 1.5f,
        cloudinessPercentage = 23,
        visibilityDistance = 10000,
        windSpeed = 3.5f,
        windDirection = 180,
        windGustsSpeed = 4.5f,
        precipitationProbability = 0.159f,
        weatherIconUrls = emptyList(),
    )
}


