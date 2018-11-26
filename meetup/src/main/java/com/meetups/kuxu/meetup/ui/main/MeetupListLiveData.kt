package com.meetups.kuxu.meetup.ui.main

import androidx.lifecycle.LiveData
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.domain.MeetupRepository
import com.meetups.kuxu.meetup.domain.SearchMeetupUseacase
import com.meetups.kuxu.meetup.entity.SearchQueryEntity
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.meetupRowBindingModelConverter
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

internal class MeetupListLiveData(
  private val nearMeetupRepository: MeetupRepository,
  private val searchMeetupUseacase: SearchMeetupUseacase,
  private val currentLocationService: CurrentLocationService
) : LiveData<List<MeetupRowBindingModel>>() {
  override fun onActive() {
    super.onActive()
    GlobalScope.launch(Dispatchers.IO) {
      try {

        val meetupList = nearMeetupRepository.loadMeetupList().receive()

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
        val searchQuery = SearchQueryEntity(
          meetupSearchBindingModel.nearSearch,
          meetupSearchBindingModel.keyword
        )
        val searchResultList = searchMeetupUseacase.execute(searchQuery).receive()
        launch(Dispatchers.Main) {
          value = meetupRowBindingModelConverter.convert(searchResultList)
        }
      } catch (e: java.lang.Exception) {
        launch(Dispatchers.Main) {
          value = emptyList()
        }
      }
    }
  }
}