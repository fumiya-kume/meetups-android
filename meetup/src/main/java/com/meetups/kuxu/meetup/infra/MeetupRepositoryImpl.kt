package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.entity.LocationEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

internal class MeetupRepositoryImpl(
  private val meetupListDataStore: MeetupListDataStore
) : MeetupRepository {

  override fun loadMeetupList(): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    val hoge = meetupListDataStore.loadMeetupList().receive().map {
      val meetupLocation = LocationEntity(it.lat, it.lon)
      MeetupEntity(
        it.id,
        it.title,
        it.event_url,
        0,
        meetupLocation
      )
    }
    send(hoge)
  }

  override fun searchMeetupList(keyword: String): ReceiveChannel<List<MeetupEntity>> = GlobalScope.produce {
    val hoge = meetupListDataStore.searchMeetupList(keyword).receive().map {
      val meetupLocation = LocationEntity(it.lat, it.lon)
      MeetupEntity(
        it.id,
        it.title,
        it.event_url,
        0,
        meetupLocation
      )
    }

    send(hoge)
  }
}
