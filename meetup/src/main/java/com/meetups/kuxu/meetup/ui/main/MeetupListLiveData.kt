package com.meetups.kuxu.meetup.ui.main

import androidx.lifecycle.LiveData
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.meetupRowBindingModelConverter
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

internal class MeetupListLiveData(
  private val meetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService
) : LiveData<List<MeetupRowBindingModel>>() {
  override fun onActive() {
    super.onActive()
    GlobalScope.launch(Dispatchers.IO) {
      try {

        val meetupList = meetupRepository.loadMeetupList().receive()

        launch(Dispatchers.Main) {
          value = meetupRowBindingModelConverter.convert(meetupList)
        }

        val newList = meetupList.map {
          val locationDistance =
            currentLocationService.distanceKmToCurrentLocation(it.meetupLocation).openSubscription().receive()
          it.copy(distance = locationDistance)
        }

        launch(Dispatchers.Main) {
          value = meetupRowBindingModelConverter.convert(newList)
        }

      } catch (e: Exception) {
        GlobalScope.launch(Dispatchers.Main) {
          value = emptyList()
        }
      }
    }
  }

  fun search(meetupSearchBindingModel: MeetupSearchBindingModel) {
    GlobalScope.launch {

      try {
        val keyword = meetupSearchBindingModel.keyword
        GlobalScope.launch(Dispatchers.IO) {
          val searchResult = meetupRepository.searchMeetupList(keyword)
            .receive()

          launch(Dispatchers.Main) {
            value = meetupRowBindingModelConverter.convert(searchResult)
          }

          val newList = searchResult.map {
            val locationDistance =
              currentLocationService.distanceKmToCurrentLocation(it.meetupLocation).openSubscription().receive()
            it.copy(distance = locationDistance)
          }

          launch(Dispatchers.Main) {
            value = meetupRowBindingModelConverter.convert(newList)
          }
        }
      } catch (e: Exception) {
        launch(Dispatchers.Main) {
          value = emptyList()
        }
      }
    }
  }
}