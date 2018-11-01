package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.domain.SearchMeetupUseacase

internal class MeetupListLiveDataFactory(
  private val nearMeetupRepository: MeetupRepository,
  private val searchMeetupUseacase: SearchMeetupUseacase

) {
  fun create(): MeetupListLiveData = MeetupListLiveData(
    nearMeetupRepository,
    searchMeetupUseacase
  )
}