package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.experimental.channels.ReceiveChannel

internal interface MeetupRepository {
  fun loadNearMeetupList(): ReceiveChannel<List<MeetupEntity>>
  fun loadMeetupList(): ReceiveChannel<List<MeetupEntity>>
  fun loadMeetupListWithKeyword(): ReceiveChannel<List<MeetupEntity>>
}