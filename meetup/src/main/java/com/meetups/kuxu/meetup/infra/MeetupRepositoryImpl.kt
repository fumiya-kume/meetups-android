package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.entity.LocationEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.runBlocking

internal class MeetupRepositoryImpl(
  private val meetupListDataStore: MeetupListDataStore
) : MeetupRepository {

  override fun loadMeetupList(): Deferred<List<MeetupEntity>?> = GlobalScope.async {
    runBlocking {
      val meetuplist = meetupListDataStore.loadMeetupList().await()
      meetuplist?.let {
        it.map {
          val meetupLocation = LocationEntity(it.lat, it.lon)
          MeetupEntity(
            it.id,
            it.title,
            it.event_url,
            0,
            meetupLocation
          )
        }
      }
    }
  }

  override fun searchMeetupList(keyword: String): Deferred<List<MeetupEntity>?> = GlobalScope.async {
    runBlocking {
      val meetupList = meetupListDataStore.searchMeetupList(keyword).await()
      meetupList?.let {
        it.map {
          MeetupEntity(
            it.id,
            it.title,
            it.event_url,
            0,
            LocationEntity(it.lat, it.lon)
          )
        }
      }
    }
  }
}
