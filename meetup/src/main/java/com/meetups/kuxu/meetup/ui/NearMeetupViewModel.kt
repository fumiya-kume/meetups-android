package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.ViewModel

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: NearMeetupListLiveDataFactory
) : ViewModel() {
  val nearMeetupListLiveData = nearMeetupListLiveDataFactory.create()
}