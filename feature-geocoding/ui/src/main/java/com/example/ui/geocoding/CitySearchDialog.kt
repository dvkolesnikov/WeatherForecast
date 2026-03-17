package com.example.ui.geocoding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.domain.geocoding.model.CityLocation
import com.example.presentation_core.theme.WeatherForecastTheme

@Composable
fun CitySearchDialog(
    query: String,
    cities: List<CityLocation>,
    onQueryChange: (String) -> Unit,
    onCitySelected: (CityLocation) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = stringResource(R.string.geocoding_search_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                )
                CitySearchWidget(
                    modifier = Modifier.fillMaxWidth(),
                    query = query,
                    cities = cities,
                    onQueryChange = onQueryChange,
                    onCitySelected = onCitySelected,
                )
                TextButton(
                    modifier = Modifier.align(androidx.compose.ui.Alignment.End),
                    onClick = onDismissRequest,
                ) {
                    Text(text = stringResource(R.string.geocoding_search_dialog_close))
                }
            }
        }
    }
}

@Composable
@Preview
private fun CitySearchDialogPreview() {
    WeatherForecastTheme {
        CitySearchDialog(
            query = "Lon",
            cities = listOf(
                CityLocation(
                    name = "London",
                    latitude = 51.5072,
                    longitude = -0.1276,
                    country = "GB",
                ),
                CityLocation(
                    name = "London",
                    latitude = 39.8865,
                    longitude = -83.4483,
                    country = "US",
                    state = "Ohio",
                ),
            ),
            onQueryChange = {},
            onCitySelected = {},
            onDismissRequest = {},
        )
    }
}
