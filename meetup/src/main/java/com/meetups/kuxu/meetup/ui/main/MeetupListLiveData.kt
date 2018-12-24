package com.meetups.kuxu.meetup.ui.main

import androidx.lifecycle.LiveData
import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.usecase.LoadNearMeetupUsecase
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.meetupRowBindingModelConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

internal class MeetupListLiveData(
  private val meetupRepository: MeetupRepository,
  private val currentLocationService: CurrentLocationService,
  private val loadNearMeetupUsecase: LoadNearMeetupUsecase
) : LiveData<List<MeetupRowBindingModel>>() {
  override fun onActive() {
    super.onActive()
    GlobalScope.launch(Dispatchers.IO) {
      searchNearMeetup()
    }
  }

  fun search(
    meetupSearchBindingModel: MeetupSearchBindingModel,
    onError: (String) -> Unit
  ) {
    GlobalScope.launch {

      try {
        val keyword = meetupSearchBindingModel.keyword
        GlobalScope.launch(Dispatchers.IO) {
          val searchResult = meetupRepository.searchMeetupList(keyword).await()!!

          launch(Dispatchers.Main) {
            value = meetupRowBindingModelConverter.convert(searchResult)
          }

          val newList = searchResult.map {
            val locationDistance =
              currentLocationService.distanceTo(it.meetupLocation).await()
            it.copy(distance = locationDistance)
          }

          launch(Dispatchers.Main) {
            value = meetupRowBindingModelConverter.convert(newList)
          }
        }
      } catch (e: Exception) {
        launch(Dispatchers.Main) {
          value = emptyList()
          onError("Error on search")
        }
      }
    }
  }

  @ExperimentalCoroutinesApi
  fun searchNearMeetup() {
    GlobalScope.launch {
      try {
        loadNearMeetupUsecase.execute()
          .consumeEach {
            launch(Dispatchers.Main) {
              it.let {
                value = meetupRowBindingModelConverter.convert(it)
              }
            }
          }
      } catch (e: java.lang.Exception) {
        launch(Dispatchers.Main) {
          value = emptyList()
        }
      }
    }
  }
}