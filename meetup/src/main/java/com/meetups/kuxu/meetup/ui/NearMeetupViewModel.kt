package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.ViewModel

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: MeetupListLiveDataFactory
) : ViewModel() {
  var meetupListLiveData = nearMeetupListLiveDataFactory.create()
}