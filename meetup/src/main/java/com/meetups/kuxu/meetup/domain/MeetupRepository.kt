package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.ReceiveChannel

internal interface MeetupRepository {
  fun loadMeetupList(): Deferred<List<MeetupEntity>?>
  fun searchMeetupList(keyword: String): Deferred<List<MeetupEntity>?>
}