package com.meetups.kuxu.meetup.domain.repository

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.MeetupEntityConverter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

internal class MeetupRepositoryImpl(
  private val meetupListDataStore: MeetupListDataStore
) : MeetupRepository {

  override fun loadMeetupList(): Deferred<List<MeetupEntity>?> = GlobalScope.async {
    runBlocking {
      meetupListDataStore.loadMeetupList()
        .await()?.let {
          MeetupEntityConverter.convert(it)
        }
    }
  }

  override fun searchMeetupList(keyword: String): Deferred<List<MeetupEntity>?> = GlobalScope.async {
    runBlocking {
      meetupListDataStore.searchMeetupList(keyword)
        .await()?.let {
          MeetupEntityConverter.convert(it)
        }
    }
  }
}
