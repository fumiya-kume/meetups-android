package com.meetups.kuxu.meetup.ui.main

import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository

internal class MeetupListLiveDataFactory(
  private val nearMeetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService

) {
  fun create(): MeetupListLiveData = MeetupListLiveData(
    nearMeetupRepository,
    currentLocationService
  )
}