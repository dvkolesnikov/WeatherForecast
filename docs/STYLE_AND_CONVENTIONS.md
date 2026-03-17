# WeatherForecast – Style and Conventions

## Kotlin

- **Official code style**: `kotlin.code.style=official` in `gradle.properties`.
- Prefer **expression bodies** for one-liners; keep functions readable.
- Use **named arguments** for clarity when there are multiple parameters (e.g. in constructors and DI).
- **Sealed classes** for UI state: `data object` for single states (e.g. `Loading`), `data class` for states with payload (e.g. `Error(message)`, `Success(...)`).
- **Result** for operations that can fail: repositories and use cases return `Result<T>`; ViewModels use `.fold(onSuccess = ..., onFailure = ...)`.
- **Coroutines**: use `viewModelScope.launch` in ViewModels; suspend functions in repository and use case.

## Android & Gradle

- **compileSdk**: use the same pattern as existing modules (e.g. `version = release(36) { minorApiLevel = 1 }`).
- **minSdk**: 24; **targetSdk**: 36; **JVM**: 11.
- **Namespace**: one per module; matches package structure (e.g. `com.example.core_network`, `com.example.ui`).
- Dependencies: use **version catalog** in `gradle/libs.versions.toml`; reference as `libs.xxx` in `build.gradle.kts`. Add new libraries and versions there first.

## Dependency Injection (Koin)

- **Modules**: one logical module per layer/feature (e.g. `appModule`, `coreNetworkModule`, `featureWeatherDomainModule`, `featureWeatherDataModule`, `viewModelModule`).
- **Naming**: `commonModule` aggregates core + feature modules; app adds `appModule` and `viewModelModule`.
- **Definitions**: use `single` for app-scoped or shared instances (e.g. Retrofit, Repository); `factory` for per-request or lightweight (e.g. UseCase, API interface from Retrofit).
- ViewModels: `viewModel { XxxViewModel(...) }` in a dedicated module; inject use cases with `get()`.

## UI (Jetpack Compose)

- **Theme**: use `WeatherForecastTheme` from `presentation-core` for all composables; use `MaterialTheme.colorScheme`, `MaterialTheme.typography` (no hardcoded colors/typography in feature UI).
- **Screens**: top-level composable is the screen (e.g. `WeatherScreen`); it receives `modifier` and optional `viewModel` (default `koinViewModel()`). Private composables for content (e.g. `WeatherScreenContent`, `LoadingContent`, `SuccessContent`).
- **State**: ViewModel exposes `StateFlow<ScreenState>`; screen uses `collectAsState()` and passes state down. Prefer sealed class for screen state (Loading / Error / Success).
- **Widgets**: reusable pieces live in feature `ui` (e.g. `CurrentWeatherWidget`, `HourlyWeatherWidget`). Prefer `Modifier` as first parameter with default `Modifier`; then data parameters.
- **Previews**: use `@Preview` and wrap in `WeatherForecastTheme { ... }` so theme is applied.
- **Internal composables**: use `private` or `internal` for screen-specific or widget-internal composables (e.g. `TemperatureInfo` in `TemperatureInfoWidget.kt`).
- **Resources**: use `stringResource(R.string.xxx)` and `painterResource(R.drawable.xxx)` from the module that owns the resource (app or feature-ui).

## Data Layer

- **API**: Retrofit interface in `data/api`; suspend functions returning `Result<Dto>` when using Result call adapter.
- **DTOs**: in `data/dto`; use `@Serializable` and `@SerialName` for JSON; names match API or clarify (e.g. `latitude`/`longitude`).
- **Mappers**: in `data/mapper`; extension functions `Dto.mapToDomain(...)` that return domain model. Compose from smaller mappers (e.g. `CurrentWeatherDto.mapToDomain`, `HourlyWeatherDto.mapToDomain`) in a root mapper (e.g. `WeatherResponse.mapToDomain`).
- **Repository**: implementation in `data/repository`; class name `XxxRepositoryImpl`, implements interface from domain. Injects API and config; maps DTO to domain and returns `Result<Domain>`.

## Domain Layer

- **Models**: immutable `data class` in `domain/model`; no framework annotations.
- **Repository**: interface in `domain/repository`; suspend functions returning `Result<T>`.
- **UseCases**: class in `domain/usecase` with `operator fun invoke(...)`. Single responsibility; depend only on repository (and domain types). Return `Result<T>`.

## File and Class Naming

- **ViewModel**: `XxxViewModel`; lives in app (or feature) module that owns the screen.
- **Screen state**: `XxxScreenState` sealed class next to the screen/ViewModel.
- **DI**: `DI.kt` or `AppModule.kt` per module; expose `val xxxModule = module { ... }`.
- **Extensions**: group by concern (e.g. `DateTimeExt.kt` for time formatting); name as `Receiver.functionName`.

## General

- Prefer **meaningful names**; avoid abbreviations except common ones (e.g. `lat`, `lon` in API).
- **TODOs**: use `// TODO` for known tech debt (e.g. hardcoded coordinates, API key in release, logging in release).
- Keep **single responsibility** per class/function; extract small composables and private functions when it improves readability.
