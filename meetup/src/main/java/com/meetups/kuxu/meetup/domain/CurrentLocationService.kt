package com.meetups.kuxu.meetup.domain

import android.location.Location
import com.google.android.gms.tasks.Task
import com.meetups.kuxu.meetup.entity.LocationEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.BroadcastChannel

internal interface CurrentLocationService {
  suspend fun loadCurrentLocation(): LocationEntity
  fun distanceKmToCurrentLocation(locationEntity: LocationEntity): Deferred<Int>
}
