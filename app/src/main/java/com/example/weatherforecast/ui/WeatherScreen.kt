package com.example.weatherforecast.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ui.weather.CurrentWeatherWidget
import com.example.ui.weather.HourlyWeatherWidget
import com.example.weatherforecast.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = koinViewModel(),
) {

    val screenState by viewModel.screenState.collectAsState()

    WeatherScreenContent(
        modifier = modifier,
        screenState = screenState,
    )
}

@Composable
private fun WeatherScreenContent(
    modifier: Modifier = Modifier,
    screenState: WeatherScreenState,
) {
    Scaffold(modifier = modifier) { innerPadding ->

        when (screenState) {
            is WeatherScreenState.Error -> ErrorContent(
                modifier = Modifier.padding(innerPadding),
                errorMessage = screenState.message
            )

            WeatherScreenState.Loading -> LoadingContent(
                modifier = Modifier.padding(innerPadding),
            )

            is WeatherScreenState.Success -> SuccessContent(
                modifier = Modifier.padding(innerPadding),
                state = screenState,

                )
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Text(text = errorMessage)
    }
}

@Composable
private fun SuccessContent(
    modifier: Modifier = Modifier,
    state: WeatherScreenState.Success
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(
                state = rememberScrollState(),
            )
    ) {
        state.currentWeather?.let {
            WeatherCard(
                modifier = Modifier
                    .fillMaxWidth(),
                subTitle = stringResource(R.string.weather_card_title_now)
            ) {
                CurrentWeatherWidget(
                    modifier = Modifier.padding(vertical = 8.dp),
                    currentWeather = state.currentWeather,
                    temperatureUnit = state.temperatureUnit,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        state.hourlyWeather.takeIf { it.isNotEmpty() }?.let {
            WeatherCard(
                modifier = Modifier.fillMaxWidth(),
                subTitle = stringResource(R.string.weather_card_title_current_day)
            ) {
                HourlyWeatherWidget(
                    hourlyWeatherItems = state.hourlyWeather,
                    tempUnit = state.temperatureUnit,
                )
            }
        }
    }
}

@Composable
private fun WeatherCard(
    modifier: Modifier = Modifier,
    subTitle: String,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .border(1.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp)),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = subTitle
            )
            content()
        }
    }
}
