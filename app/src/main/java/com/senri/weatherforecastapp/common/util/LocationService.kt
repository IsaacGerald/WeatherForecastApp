package com.senri.weatherforecastapp.common.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocationService(private val context: Context) {
    private val _locationState = MutableStateFlow<LocationState>(LocationState.Idle)
    val locationState: StateFlow<LocationState> = _locationState

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val UPDATE_INTERVAL = 5 * 60 * 1000L // 5 minutes

    private val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_BALANCED_POWER_ACCURACY,
        10000L // 10 seconds
    ).apply {
        setMinUpdateIntervalMillis(10000L) // 5 seconds
        setMaxUpdateDelayMillis(2000L)
    }.build()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            locationResult.lastLocation?.let { location ->
                _locationState.value = LocationState.Success(location)
            }
        }

        override fun onLocationAvailability(availability: LocationAvailability) {
            if (!availability.isLocationAvailable) {
                _locationState.value = LocationState.Error("Location not available")
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        _locationState.value = LocationState.Loading
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
        _locationState.value = LocationState.Idle
    }
}

sealed class LocationState {
    object Idle : LocationState()
    object Loading : LocationState()
    data class Success(val location: Location) : LocationState()
    data class Error(val message: String) : LocationState()
}