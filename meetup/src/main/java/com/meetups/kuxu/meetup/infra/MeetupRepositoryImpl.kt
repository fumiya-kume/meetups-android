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
  override fun loadMeetupListWithKeyword(): ReceiveChannel<List<MeetupEntity>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loadMeetupList(): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    val hoge = meetupListDataStore.loadMeetupList().receive().events.map {
      val meetupLocation = LocationEntity(it.lat ?: 0.0, it.lon ?: 0.0)
      MeetupEntity(
        it.eventId,
        it.title,
        it.eventUrl,
        currentLocationService.distanceKmToCurrentLocation(meetupLocation).openSubscription().receive(),
        meetupLocation
      )
    }
    send(hoge)
  }

  override fun loadNearMeetupList(): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    val DistanceLimit = 100
    val meetupList = loadMeetupList().receive()
    val filteredList =
      meetupList.filter {
        DistanceLimit > it.distance
      }
    send(filteredList)
  }
}
