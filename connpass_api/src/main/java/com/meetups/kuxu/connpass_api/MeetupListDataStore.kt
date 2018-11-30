package com.meetups.kuxu.connpass_api

import com.meetups.kuxu.connpass_api.entity.EventJson
import kotlinx.coroutines.channels.ReceiveChannel

public interface MeetupListDataStore {
  fun loadMeetupList(): ReceiveChannel<List<EventJson>>
  fun searchMeetupList(keyword: String): ReceiveChannel<List<EventJson>>
}