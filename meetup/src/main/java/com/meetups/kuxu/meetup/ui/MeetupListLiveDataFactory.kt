package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.domain.MeetupRepository

internal class MeetupListLiveDataFactory(
  private val nearMeetupRepository: MeetupRepository
  ) {
  fun create(): MeetupListLiveData = MeetupListLiveData(
    nearMeetupRepository
  )
}