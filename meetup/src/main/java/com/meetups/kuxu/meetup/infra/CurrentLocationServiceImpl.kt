package com.meetups.kuxu.meetup.infra

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.entity.LocationEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

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

  @SuppressLint("MissingPermission")
  override fun loadCurrentLocation(): BroadcastChannel<LocationEntity> {
    val broadcast = ConflatedBroadcastChannel<LocationEntity>()
    try {
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
    } catch (e: Exception) {
      throw e
    }
    return broadcast
  }

}