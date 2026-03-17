package com.example.ui.geocoding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.domain.geocoding.model.CityLocation
import com.example.presentation_core.theme.WeatherForecastTheme

@Composable
fun CitySearchWidget(
    modifier: Modifier = Modifier,
    query: String,
    cities: List<CityLocation>,
    onQueryChange: (String) -> Unit,
    onCitySelected: (CityLocation) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = onQueryChange,
            label = { Text(text = stringResource(R.string.geocoding_search_label)) },
            placeholder = { Text(text = stringResource(R.string.geocoding_search_placeholder)) },
            singleLine = true,
        )

        when {
            query.isBlank() -> SearchHintText(text = stringResource(R.string.geocoding_search_empty))

            cities.isEmpty() -> SearchHintText(text = stringResource(R.string.geocoding_search_no_results))

            else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(cities) { city ->
                    CityLocationCard(
                        city = city,
                        onClick = { onCitySelected(city) },
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchHintText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(horizontal = 4.dp),
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
private fun CityLocationCard(
    city: CityLocation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = city.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = buildCitySubtitle(country = city.country, state = city.state),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

private fun buildCitySubtitle(
    country: String,
    state: String?,
): String = buildString {
    append(country)
    state?.takeIf { it.isNotBlank() }?.let {
        append(" • ")
        append(it)
    }
}

@Composable
@Preview
private fun CitySearchWidgetPreview(
    @PreviewParameter(CitySearchPreviewProvider::class) previewState: CitySearchPreviewState,
) {
    WeatherForecastTheme {
        CitySearchWidget(
            query = previewState.query,
            cities = previewState.cities,
            onQueryChange = {},
            onCitySelected = {},
        )
    }
}

private data class CitySearchPreviewState(
    val query: String,
    val cities: List<CityLocation>,
)

private class CitySearchPreviewProvider :
    CollectionPreviewParameterProvider<CitySearchPreviewState>(
        listOf(
            CitySearchPreviewState(
                query = "",
                cities = emptyList(),
            ),
            CitySearchPreviewState(
                query = "Lon",
                cities = emptyList(),
            ),
            CitySearchPreviewState(
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
            ),
        ),
    )
