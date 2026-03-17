# WeatherForecast – Technologies and Versions

## Stack Summary

| Area        | Technology |
|------------|------------|
| Language   | Kotlin 2.2.10 |
| UI         | Jetpack Compose (Material 3, BOM 2026.03.00) |
| DI         | Koin 4.1.1 |
| Networking | OkHttp 5.3.2, Retrofit 3.0.0, kotlinx.serialization |
| Images     | Coil 3.4.0 (Compose + OkHttp) |
| Build      | AGP 9.1.0, Gradle 9.x, Kotlin Compose plugin |

## Version Catalog

All dependency and plugin versions are centralized in **`gradle/libs.versions.toml`**.

- **[versions]**: agp, kotlin, composeBom, koin, retrofit, okhttp, etc.
- **[libraries]**: androidx-*, koin-*, retrofit, okhttp, coil-compose, kotlinx-serialization-json, etc.
- **[plugins]**: android-application, android-library, kotlin-compose, kotlin-serialization, jetbrains-kotlin-jvm.

When adding a dependency:

1. Add a version in `[versions]` if new.
2. Add the library entry in `[libraries]` (or re-use existing).
3. In the module `build.gradle.kts`, use `implementation(libs.xxx)` or `alias(libs.plugins.xxx)`.

## Key Libraries by Module

- **app**: AndroidX Core, Lifecycle, Activity Compose, Compose BOM, Material3, Koin Android + Compose.
- **presentation-core**: Compose BOM, UI, Material3 only (no Koin).
- **data-core**: OkHttp, Logging Interceptor, Retrofit, kotlinx.serialization JSON, Result call adapter, Koin.
- **feature-weather:data**: data-core, domain-core, feature-weather:domain, kotlinx.serialization, Retrofit, Koin.
- **feature-weather:ui**: presentation-core, feature-weather:domain, Compose, Coil Compose + Coil OkHttp, Material.
- **feature-weather:domain**: Koin Core only (for DI).
- **di**: Koin Android; depends on data-core, feature-weather:domain, feature-weather:data.

## API and Configuration

- **Weather API**: OpenWeatherMap (One Call 3.0); base URL and image base URL from `BuildConfig` (API_BASE_URL, IMAGES_BASE_URL) and injected as `AppConfig` via Koin.
- **Auth**: API key added in `data-core` via `AuthInterceptor` (query param `appid`). For production, key should not be committed; use build config or secrets.

## Compose and Theme

- **BOM**: Compose version is driven by `androidx.compose:compose-bom`; prefer BOM for Compose dependencies to align versions.
- **Theme**: Material 3 with custom `WeatherForecastTheme` (light/dark, optional dynamic color on API 31+). Colors and typography defined in `presentation-core`.

## Testing

- **Unit**: JUnit 4 (`libs.junit`) in app, data-core, feature-weather.
- **Instrumented**: AndroidX Test, Espresso, Compose UI Test (app module).

## Kotlin and JVM

- **Kotlin code style**: official (`gradle.properties`).
- **JVM target**: 11 (Java 11 source/target in all modules).
- **Kotlin plugins**: Compose (app, presentation-core, feature-weather:ui), Serialization (data-core, feature-weather:data), JVM (domain-core, feature-weather:domain).
