package com.meetups.kuxu.connpass_api

import com.meetups.kuxu.connpass_api.entity.MeetupJson

public interface MeetupListDataStore {
  fun loadMeetupList(): MeetupJson
}