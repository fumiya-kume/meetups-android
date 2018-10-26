package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.CurrentLocationEntity
import io.reactivex.Maybe

internal interface CurrentLocationService {
  fun loadCurrentLocation(): Maybe<CurrentLocationEntity>
}
