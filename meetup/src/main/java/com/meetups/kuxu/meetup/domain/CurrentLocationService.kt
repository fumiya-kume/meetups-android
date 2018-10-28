package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.LocationEntity
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.ReceiveChannel

internal interface CurrentLocationService {
  fun loadCurrentLocation(): BroadcastChannel<LocationEntity>
  fun distanceKmToCurrentLocation(locationEntity: LocationEntity): BroadcastChannel<Int>
}
