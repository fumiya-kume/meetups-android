package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.experimental.channels.ReceiveChannel

internal interface MeetupRepository {
  fun loadMeetupList(): ReceiveChannel<List<MeetupEntity>>
  fun searchMeetupList(keyword: String): ReceiveChannel<List<MeetupEntity>>
}