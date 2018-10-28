package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.ui.bindingModel.CurrentLocationBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.CurrentLocationBindingModelConverter
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.launch

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: MeetupListLiveDataFactory,
  private val currentLocationService: CurrentLocationService
) : ViewModel() {
  var meetupListLiveData = nearMeetupListLiveDataFactory.create()
  var currentLocationLiveData = MutableLiveData<CurrentLocationBindingModel>()

  fun loadCurrentLocation(
    onError: (message: String) -> Unit
  ) {
    GlobalScope.launch {
      try {
        GlobalScope.launch(Dispatchers.IO) {
          currentLocationService.loadCurrentLocation().openSubscription()
            .consumeEach {
              GlobalScope.launch(Dispatchers.Main) {
                currentLocationLiveData.value = CurrentLocationBindingModelConverter.convert(it)
              }
            }
        }
      } catch (e: Exception) {
        onError("エラーが発生しました...")
      }
    }
  }

  fun searchWithLocation(
    onCurrentLocationMissing: (message: String) -> Unit
  ) {
    val currentLocationSnapshot = currentLocationLiveData.value
    if (currentLocationSnapshot == null) {
      onCurrentLocationMissing("現在地の取得に失敗しました")
    }
    currentLocationSnapshot?.let {
      meetupListLiveData.loadNearMeetup()
    }
  }
}