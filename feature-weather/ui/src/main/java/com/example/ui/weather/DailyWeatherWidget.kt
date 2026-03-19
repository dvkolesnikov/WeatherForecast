package com.example.ui.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.weather.model.DailyWeather
import com.example.presentation_core.ext.toShortDate
import com.example.presentation_core.theme.WeatherForecastTheme
import com.example.ui.weather.common.PrecipitationInfoWidget
import com.example.ui.weather.common.SolarTimeInfoWidget
import com.example.ui.weather.common.WeatherConditionIcon
import com.example.ui.weather.ext.formatTemperature
import java.time.ZoneId

@Composable
fun DailyWeatherWidget(
    modifier: Modifier = Modifier,
    dailyWeatherItems: List<DailyWeather>,
    tempUnit: String,
    timeZone: ZoneId,
) {
    Column(
        modifier = modifier,
    ) {
        dailyWeatherItems.forEach {
            DailyWeatherItem(
                dailyWeather = it,
                tempUnit = tempUnit,
                timeZone = timeZone,
            )
        }
    }
}

@Composable
private fun DailyWeatherItem(
    modifier: Modifier = Modifier,
    dailyWeather: DailyWeather,
    tempUnit: String,
    timeZone: ZoneId,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = dailyWeather.timeStamp.toShortDate(timeZone))
                dailyWeather.weatherIconUrls.forEach {
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    WeatherConditionIcon(url = it)
                }
                Spacer(modifier = Modifier.padding(start = 8.dp))
                PrecipitationInfoWidget(
                    probability = dailyWeather.precipitationProbability,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SolarTimeInfoWidget(
                    timeStamp = dailyWeather.sunriseTimeStamp,
                    icon = painterResource(R.drawable.ic_sunrise),
                    timeZone = timeZone,
                )
                SolarTimeInfoWidget(
                    timeStamp = dailyWeather.sunsetTimeStamp,
                    icon = painterResource(R.drawable.ic_sunset),
                    timeZone = timeZone,
                )
                Text(
                    text = stringResource(
                        R.string.weather_label_temperature_range,
                        dailyWeather.minTemperature.formatTemperature(tempUnit),
                        dailyWeather.maxTemperature.formatTemperature(tempUnit)
                    )
                )
            }
        }
    }
}

@Composable
@Preview
private fun DailyWeatherWidgetPreview() {
    WeatherForecastTheme {
        DailyWeatherWidget(
            dailyWeatherItems = listOf(
                previewDailyItem,
                previewDailyItem.copy(minTemperature = -14.4f),
                previewDailyItem.copy(maxTemperature = 11.1f)
            ),
            tempUnit = "°C",
            timeZone = ZoneId.systemDefault(),
        )
    }
}

private val previewDailyItem by lazy {
    DailyWeather(
        timeStamp = (System.currentTimeMillis() / 1000).toInt(),
        precipitationProbability = 0.159f,
        weatherIconUrls = emptyList(),
        sunriseTimeStamp = (System.currentTimeMillis() / 1000).toInt(),
        sunsetTimeStamp = (System.currentTimeMillis() / 1000).toInt(),
        minTemperature = -35.5f,
        maxTemperature = 49.9f,
    )
}
