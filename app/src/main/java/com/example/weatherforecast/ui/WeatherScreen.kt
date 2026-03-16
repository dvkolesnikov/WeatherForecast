package com.example.weatherforecast.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.ui.weather.CurrentWeatherWidget
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
    CurrentWeatherWidget(
        modifier = modifier,
        currentWeather = state.currentWeather,
    )
}
