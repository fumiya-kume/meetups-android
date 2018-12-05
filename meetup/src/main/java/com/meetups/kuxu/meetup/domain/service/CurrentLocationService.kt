package com.meetups.kuxu.meetup.domain.service

import com.meetups.kuxu.meetup.entity.LocationEntity
import kotlinx.coroutines.Deferred

internal interface CurrentLocationService {
  suspend fun loadCurrentLocation(): LocationEntity
  fun distanceTo(locationEntity: LocationEntity): Deferred<Int>
}
