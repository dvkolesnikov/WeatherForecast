package com.example.domain_core.repository

import com.example.domain_core.model.Location

interface LocationRepository {
    suspend fun getCurrentLocation(): Result<Location>
}
