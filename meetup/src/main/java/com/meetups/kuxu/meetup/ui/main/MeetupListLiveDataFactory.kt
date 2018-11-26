package com.meetups.kuxu.meetup.ui.main

import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.domain.SearchMeetupUseacase

internal class MeetupListLiveDataFactory(
  private val nearMeetupRepository: MeetupRepository,
  private val searchMeetupUseacase: SearchMeetupUseacase,
  private val currentLocationService: CurrentLocationService

) {
  fun create(): MeetupListLiveData = MeetupListLiveData(
    nearMeetupRepository,
    searchMeetupUseacase,
    currentLocationService
  )
}