package com.meetups.kuxu.connpass_api

import com.meetups.kuxu.connpass_api.entity.EventJson
import kotlinx.coroutines.Deferred

public interface MeetupListDataStore {
  fun loadMeetupList(): Deferred<List<EventJson>?>
  fun searchMeetupList(keyword: String): Deferred<List<EventJson>?>
}