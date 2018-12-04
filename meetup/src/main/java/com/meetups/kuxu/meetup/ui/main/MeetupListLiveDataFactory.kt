package com.meetups.kuxu.meetup.ui.main

import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.domain.SearchMeetupUsecase

internal class MeetupListLiveDataFactory(
  private val nearMeetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService,
  private val searchMeetupUsecase: SearchMeetupUsecase

) {
  fun create(): MeetupListLiveData = MeetupListLiveData(
    nearMeetupRepository,
    currentLocationService,
    searchMeetupUsecase
  )
}