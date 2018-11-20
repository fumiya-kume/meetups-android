package com.meetups.kuxu.meetup.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.meetups.kuxu.meetup.service.ConnpassEventPageViewerService
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: MeetupListLiveDataFactory,
  private val connpassEventPageViewerService: ConnpassEventPageViewerService,
  application: Application
) : AndroidViewModel(application) {
  var meetupListLiveData = nearMeetupListLiveDataFactory.create()

  fun search(bindingModel: MeetupSearchBindingModel) {
    meetupListLiveData.search(bindingModel)
  }

  fun showEventPage(bindingModel: MeetupRowBindingModel) {
    if(bindingModel.meetupUrl.isEmpty()){
      return
    }
    connpassEventPageViewerService.showEventPage(bindingModel.meetupUrl)
  }
}