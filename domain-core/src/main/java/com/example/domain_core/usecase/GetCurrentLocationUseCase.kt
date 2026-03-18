package com.example.domain_core.usecase

import com.example.domain_core.model.Location
import com.example.domain_core.repository.LocationRepository

class GetCurrentLocationUseCase(
    private val repository: LocationRepository,
) {
    suspend operator fun invoke(): Result<Location> {
        return repository.getCurrentLocation()
    }
}