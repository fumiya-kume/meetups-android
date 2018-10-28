package com.meetups.kuxu.meetup.infra

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.core.content.PermissionChecker
import com.google.android.gms.location.FusedLocationProviderClient
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.entity.LocationEntity
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.launch

internal class CurrentLocationServiceImpl(
  private val context: Context
) : CurrentLocationService {

  override fun distanceKmToCurrentLocation(locationEntity: LocationEntity): BroadcastChannel<Int> {
    val broadcast = BroadcastChannel<Int>(10)
    GlobalScope.launch(Dispatchers.Unconfined) {
      loadCurrentLocation().consumeEach {
        val resultList = FloatArray(3)
        Location.distanceBetween(it.lat, it.lon, locationEntity.lat, locationEntity.lon, resultList)
        val km = (resultList[0] / 1000).toInt()
        broadcast.send(km)
      }
    }
    return broadcast
  }

  override fun loadCurrentLocation(): BroadcastChannel<LocationEntity> {
    val broadcast = ConflatedBroadcastChannel<LocationEntity>()
    try {
      if (PermissionChecker.checkSelfPermission(
          context,
          Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED
      ) {
        val locationProviderClient = FusedLocationProviderClient(context)
        val lastLocation = locationProviderClient.lastLocation
        lastLocation.addOnSuccessListener {
          GlobalScope.launch(Dispatchers.Unconfined) {
            val currenLocation = LocationEntity(
              it.latitude,
              it.longitude
            )
            broadcast.send(currenLocation)
          }
        }
      }
    } catch (e: Exception) {
      throw e
    }
    return broadcast
  }

}