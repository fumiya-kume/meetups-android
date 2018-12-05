package com.meetups.kuxu.meetup.domain.service

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.meetups.kuxu.meetup.entity.LocationEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class CurrentLocationServiceImpl(
  private val context: Context
) : CurrentLocationService {

  override fun distanceTo(locationEntity: LocationEntity) = GlobalScope.async {
    runBlocking {
      val currentLocation = loadCurrentLocation()
      val resultList = FloatArray(3)
      Location.distanceBetween(
        currentLocation.lat,
        currentLocation.lon,
        locationEntity.lat,
        locationEntity.lon,
        resultList
      )
      val km = (resultList[0] / 1000).toInt()
      return@runBlocking km
    }
  }

  @SuppressLint("MissingPermission")
  override suspend fun loadCurrentLocation() = suspendCoroutine<LocationEntity> { cont ->
    try {
      val locationProviderClient = FusedLocationProviderClient(context)
      val lastLocation = locationProviderClient.lastLocation
      lastLocation.addOnSuccessListener {
        cont.resume(
          LocationEntity(
            it.latitude,
            it.longitude
          )
        )
      }
    } catch (e: Exception) {
      throw e
    }
  }
}