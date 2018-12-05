package com.meetups.kuxu.meetup.domain.repository

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.entity.MeetupEntityConverter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

internal class MeetupRepositoryImpl(
  private val meetupListDataStore: MeetupListDataStore
) : MeetupRepository {

  override fun loadMeetupList() = GlobalScope.async {
    meetupListDataStore.loadMeetupList()
      .await()?.let {
        MeetupEntityConverter.convert(it)
      }
  }

  override fun searchMeetupList(keyword: String) = GlobalScope.async {
    meetupListDataStore.searchMeetupList(keyword)
      .await()?.let {
        MeetupEntityConverter.convert(it)
      }
  }
}
