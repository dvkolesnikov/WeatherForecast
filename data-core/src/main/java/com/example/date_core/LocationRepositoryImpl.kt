package com.example.date_core

import android.annotation.SuppressLint
import android.content.Context
import com.example.domain_core.model.Location
import com.example.domain_core.repository.LocationRepository
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationRepositoryImpl(
    private val context: Context
) : LocationRepository {

    private val fusedClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCurrentLocation(): Result<Location> =
        suspendCancellableCoroutine { cont ->
            fusedClient.lastLocation.addOnSuccessListener { location ->
                cont.resume(
                    value = if (location != null) {
                        Result.success(
                            Location(
                                latitude = location.latitude,
                                longitude = location.longitude
                            )
                        )
                    } else {
                        Result.failure(IllegalStateException("Location is null"))
                    }, onCancellation = null
                )
            }
                .addOnFailureListener {
                    cont.resume(
                        value = Result.failure(it),
                        onCancellation = null
                    )
                }
        }
}
