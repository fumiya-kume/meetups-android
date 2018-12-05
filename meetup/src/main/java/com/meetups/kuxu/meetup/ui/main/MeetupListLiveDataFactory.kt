package com.meetups.kuxu.meetup.ui.main

import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.usecase.LoadNearMeetupUsecase

internal class MeetupListLiveDataFactory(
  private val nearMeetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService,
  private val loadNearMeetupUsecase: LoadNearMeetupUsecase

) {
  fun create(): MeetupListLiveData = MeetupListLiveData(
    nearMeetupRepository,
    currentLocationService,
    loadNearMeetupUsecase
  )
}