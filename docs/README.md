# WeatherForecast

## Project Description

`WeatherForecast` is a modular Android app that shows weather data from OpenWeatherMap and lets users search/select cities via geocoding.

It displays current weather, 24-hour forecast and 7-days forecast for the selected city.

## Dependencies and Plugins

- **Main dependencies:** AndroidX, Jetpack Compose (Material 3), Koin, Retrofit, OkHttp, kotlinx.serialization, Coil, Play Services Location.
- **Plugins:** Android Application/Library, Kotlin Compose, Kotlin Serialization, Kotlin JVM.
- **Version catalog:** `gradle/libs.versions.toml`.

## Run

1. Open project in Android Studio and sync Gradle.
2. Run `app` on emulator/device (Android 8.0+, API 26+).

CLI options:

```bash
./gradlew :app:assembleDebug
./gradlew :app:installDebug
```

## Architecture (Short)

The project follows feature-based Clean Architecture principles:

- `app`: startup, screen orchestration, ViewModels
- `di`: Koin module aggregation
- `domain-core`, `data-core`, `presentation-core`: shared layers
- `feature-weather` and `feature-geocoding`: each split into `ui`, `domain`, `data`

Dependency rules: 
- `ui -> domain`, 
- `data -> domain`
- `app -> ui`, `app -> domain`
- domain stays framework-agnostic.

## TODO

Following points are nice-to-have, but they are not included into the app because of lack of time.

1. Error handling. Currently, the app displays raw error messages. Instead, app-specific exceptions must be added.
2. Store selected city in a persistent storage to improve UX.
3. Show Location permission rationale if the user rejected it. 