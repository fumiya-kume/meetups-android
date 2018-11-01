package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.ViewModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: MeetupListLiveDataFactory
) : ViewModel() {
  var meetupListLiveData = nearMeetupListLiveDataFactory.create()

  fun search(bindingModel: MeetupSearchBindingModel) {
    meetupListLiveData.search(bindingModel)
  }
}