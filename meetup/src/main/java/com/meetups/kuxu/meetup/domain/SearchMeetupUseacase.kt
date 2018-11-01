package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.SearchQueryEntity
import kotlinx.coroutines.experimental.channels.ReceiveChannel

internal interface SearchMeetupUseacase {
  fun execute(query: SearchQueryEntity): ReceiveChannel<List<MeetupEntity>>
}