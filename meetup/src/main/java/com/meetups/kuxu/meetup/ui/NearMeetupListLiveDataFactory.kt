package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.domain.NearMeetupRepository

internal class NearMeetupListLiveDataFactory(
  private val nearMeetupRepository: NearMeetupRepository
) {
  fun create(): NearMeetupListLiveData = NearMeetupListLiveData(
    nearMeetupRepository
  )
}