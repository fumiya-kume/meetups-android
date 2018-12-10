package com.meetups.kuxu.meetup.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.meetups.kuxu.meetup.domain.service.ConnpassEventPageViewerService
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: MeetupListLiveDataFactory,
  private val connpassEventPageViewerService: ConnpassEventPageViewerService,
  application: Application
) : AndroidViewModel(application) {
  var meetupListLiveData = nearMeetupListLiveDataFactory.create()
  val errorMessageLiveData = MutableLiveData<String>()

  fun search(bindingModel: MeetupSearchBindingModel) {
    if (bindingModel.nearSearch && bindingModel.keyword.isEmpty()
    ) {
      meetupListLiveData.searchNearMeetup()
    } else {
      meetupListLiveData.search(
        bindingModel
      ) {
        errorMessageLiveData.postValue(it)
      }
    }
  }

  fun showEventPage(bindingModel: MeetupRowBindingModel) {
    if (bindingModel.meetupUrl.isEmpty()) {
      return
    }
    connpassEventPageViewerService.showEventPage(bindingModel.meetupUrl)
  }
}