# WeatherForecast – Architecture

## Overview

WeatherForecast is an Android app built with **Clean Architecture** and **feature-based modularization**. The project uses **Kotlin**, **Jetpack Compose**, **Koin** for DI, and **Retrofit** for networking.

## Module Structure

```
WeatherForecast/
├── app/                    # Application entry point, theme wiring, ViewModels
├── di/                     # Koin module aggregation (commonModule)
├── data-core/              # Shared network stack (OkHttp, Retrofit, serialization)
├── domain-core/            # Shared domain (e.g. AppConfig)
├── presentation-core/      # Shared UI: theme (Color, Type, Theme), extensions
└── feature-weather/        # Weather feature
    ├── domain/             # Models, Repository interface, UseCases (JVM library)
    ├── data/               # API, DTOs, mappers, Repository implementation
    └── ui/                 # Compose screens and widgets
```

### Module Dependencies (Direction of Dependency)

- **app** → di, presentation-core, domain-core, feature-weather:ui, feature-weather:domain  
- **di** → data-core, feature-weather:domain, feature-weather:data  
- **feature-weather:ui** → presentation-core, feature-weather:domain  
- **feature-weather:data** → data-core, domain-core, feature-weather:domain  
- **data-core** → domain-core  

Domain and presentation-core have no dependency on Android UI or data layers.

## Layer Roles

### App

- `MainActivity`: sets Compose content and `WeatherForecastTheme`.
- `WeatherApp`: initializes Koin with `appModule`, `commonModule`, `viewModelModule`.
- App-specific DI: `AppConfig` (from `BuildConfig`), ViewModels.
- Screens and screen state live here when they orchestrate a feature (e.g. `WeatherScreen`, `WeatherScreenState`, `WeatherViewModel`).

### DI

- Aggregates all feature and core modules in `commonModule`.
- No business logic; only `module { includes(...) }`.

### Data-core

- Provides **OkHttp** (with `AuthInterceptor`, logging), **Retrofit** (JSON via kotlinx.serialization, `Result` call adapter).
- Depends only on `domain-core` (e.g. `AppConfig` for base URL).
- Namespace: `com.example.core_network`.

### Domain-core

- Shared domain types used across features (e.g. `AppConfig`).
- Pure JVM library; no Android or Compose.

### Presentation-core

- **Theme**: `WeatherForecastTheme`, `Color`, `Typography`.
- **Extensions**: e.g. `Int.toTimeHHmm()`, `toShortDate()`, `toDateTime()` for timestamps.
- Used by app and feature UI modules; no dependency on feature domain/data.

### Feature: feature-weather

- **domain**: `Weather`, `CurrentWeather`, `HourlyWeather`, `DailyWeather`; `WeatherRepository`; `GetWeatherUseCase`. Pure Kotlin/JVM.
- **data**: `WeatherApi` (Retrofit), DTOs (`WeatherResponse`, etc.), mappers (`mapToDomain` extensions), `WeatherRepositoryImpl`. Depends on `data-core`, `domain-core`, `feature-weather:domain`.
- **ui**: Compose widgets and screens (e.g. `CurrentWeatherWidget`, `HourlyWeatherWidget`). Depends on `presentation-core` and `feature-weather:domain` only (no data layer).

## Data Flow (Example: Load Weather)

1. **UI** collects user context (or uses defaults) and calls **ViewModel**.
2. **ViewModel** calls **UseCase** (e.g. `GetWeatherUseCase(lat, lon)`).
3. **UseCase** calls **Repository** interface (e.g. `WeatherRepository.loadWeather`).
4. **Repository impl** (in data) calls **API** (Retrofit), then maps **DTO → domain** with `mapToDomain(baseUrl)`.
5. UseCase can post-process (e.g. sort, take first N items) and return `Result<Weather>`.
6. ViewModel exposes state (e.g. `StateFlow<WeatherScreenState>`) and UI observes it and renders Loading / Error / Success.

## Naming and Package Conventions

- **Namespaces** (in `build.gradle.kts`): `com.example.weatherforecast`, `com.example.di`, `com.example.core_network`, `com.example.domain_core`, `com.example.presentation_core`, `com.example.feature_weather`, `com.example.ui`, `com.example.data`, `com.example.domain`.
- **Repository**: interface in `domain`; implementation in `data` with `Impl` suffix.
- **UseCases**: in `domain/usecase`, named e.g. `GetWeatherUseCase`, invoked with `operator fun invoke(...)`.
- **Mappers**: in `data/mapper`; extension functions `XxxDto.mapToDomain(...)` returning domain model.
- **DI**: each layer that contributes DI has a `DI.kt` (or similar) exposing a Koin `module`; app aggregates them.

## Adding a New Feature

1. Create `feature-<name>/` with submodules: `domain`, `data`, `ui` (if needed).
2. In `settings.gradle.kts`: `include(":feature-<name>")`, `include(":feature-<name>:domain")`, etc.
3. **domain**: models, repository interface, use cases; depend only on shared `domain-core` if needed.
4. **data**: API, DTOs, mappers, repository impl; depend on `data-core`, `domain-core`, `feature-<name>:domain`.
5. **ui**: Compose; depend on `presentation-core`, `feature-<name>:domain`.
6. **di**: add `feature<Name>DomainModule`, `feature<Name>DataModule` to `commonModule`; app or feature provides ViewModels and includes feature UI.

Keep dependencies pointing inward: UI → domain, data → domain; never domain or presentation-core depending on data or app.
