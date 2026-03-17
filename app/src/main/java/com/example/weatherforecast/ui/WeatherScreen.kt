package com.example.weatherforecast.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.domain.geocoding.model.CityLocation
import com.example.ui.geocoding.CitySearchDialog
import com.example.ui.weather.CurrentWeatherWidget
import com.example.ui.weather.DailyWeatherWidget
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
        onQueryChange = viewModel::onQueryChange,
        onCitySelected = viewModel::onCitySelected,
    )
}

@Composable
private fun WeatherScreenContent(
    modifier: Modifier = Modifier,
    screenState: WeatherScreenState,
    onQueryChange: (String) -> Unit,
    onCitySelected: (CityLocation) -> Unit,
) {
    var isCitySearchDialogVisible by rememberSaveable { mutableStateOf(false) }

    Scaffold(modifier = modifier) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextButton(onClick = { isCitySearchDialogVisible = true }) {
                Text(text = stringResource(R.string.search_city_button_label))
            }

            screenState.citySearchState.selectedCity?.let { selectedCity ->
                Text(
                    text = stringResource(R.string.selected_city_label, selectedCity.name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            when (screenState) {
                is WeatherScreenState.Error -> ErrorContent(
                    errorMessage = screenState.message
                )

                is WeatherScreenState.Loading -> LoadingContent()

                is WeatherScreenState.Success -> SuccessContent(
                    state = screenState,
                )
            }
        }
    }

    if (isCitySearchDialogVisible) {
        CitySearchDialog(
            query = screenState.citySearchState.query,
            cities = screenState.citySearchState.cities,
            onQueryChange = onQueryChange,
            onCitySelected = {
                onCitySelected(it)
                isCitySearchDialogVisible = false
            },
            onDismissRequest = {
                isCitySearchDialogVisible = false
            },
        )
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth(),
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
        modifier = modifier.fillMaxWidth(),
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
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
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
        state.dailyWeather.takeIf { it.isNotEmpty() }?.let {
            WeatherCard(
                modifier = Modifier.fillMaxWidth(),
                subTitle = stringResource(R.string.weather_card_title_week)
            ) {
                DailyWeatherWidget(
                    dailyWeatherItems = state.dailyWeather,
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
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                text = subTitle
            )
            content()
        }
    }
}
