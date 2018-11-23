package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.entity.LocationEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce

internal class MeetupRepositoryImpl(
  private val meetupListDataStore: MeetupListDataStore,
  private val currentLocationService: CurrentLocationService
) : MeetupRepository {

  override fun loadMeetupList(): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    val hoge = meetupListDataStore.loadMeetupList().receive().map {
      val meetupLocation = LocationEntity(it.lat ?: 0.0, it.lon ?: 0.0)
      MeetupEntity(
        it.id,
        it.title,
        it.event_url,
        currentLocationService.distanceKmToCurrentLocation(meetupLocation).openSubscription().receive(),
        meetupLocation
      )
    }

    send(hoge)
  }
}
