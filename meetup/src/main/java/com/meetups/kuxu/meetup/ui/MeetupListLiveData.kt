package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.LiveData
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.meetupRowBindingModelConverter
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

internal class MeetupListLiveData(
  private val nearMeetupRepository: MeetupRepository
) : LiveData<List<MeetupRowBindingModel>>() {
  override fun onActive() {
    super.onActive()
    GlobalScope.launch(Dispatchers.IO) {
      try {
        val meetupList = nearMeetupRepository.loadMeetupList().receive()
        launch(Dispatchers.Main) {
          value = meetupRowBindingModelConverter.convert(meetupList)
        }
      } catch (e: Exception) {
        GlobalScope.launch(Dispatchers.Main) {
          value = emptyList()
        }
      }
    }
  }

  fun loadNearMeetup() {
    GlobalScope.launch(Dispatchers.IO) {
      try {
        val nearMeetupList = nearMeetupRepository.loadNearMeetupList()
          .receive()
        GlobalScope.launch(Dispatchers.Main) {
          value = meetupRowBindingModelConverter.convert(nearMeetupList)
        }
      } catch (e: Exception) {
        GlobalScope.launch(Dispatchers.Main) {
          value = emptyList()
        }
      }
    }
  }
}