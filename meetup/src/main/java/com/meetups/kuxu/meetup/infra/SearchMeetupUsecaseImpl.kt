package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.meetup.domain.SearchMeetupUseacase
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.meetups.kuxu.meetup.entity.SearchQueryEntity
import kotlinx.coroutines.experimental.channels.ReceiveChannel

internal class SearchMeetupUsecaseImpl:SearchMeetupUseacase{
  override fun execute(query: SearchQueryEntity): ReceiveChannel<List<MeetupEntity>> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}