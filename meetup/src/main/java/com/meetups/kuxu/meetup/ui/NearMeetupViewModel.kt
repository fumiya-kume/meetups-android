package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.ViewModel
import com.meetups.kuxu.meetup.domain.CurrentLocationService
import com.meetups.kuxu.meetup.ui.bindingModel.CurrentLocationBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.CurrentLocationBindingModelConverter
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

internal class NearMeetupViewModel(
  nearMeetupListLiveDataFactory: NearMeetupListLiveDataFactory,
  private val currentLocationService: CurrentLocationService
) : ViewModel() {
  val nearMeetupListLiveData = nearMeetupListLiveDataFactory.create()

  fun loadCurrentLocation(
    onError: (message: String) -> Unit,
    onSuccess: (CurrentLocationBindingModel) -> Unit
  ) {
    GlobalScope.launch {
      currentLocationService.loadCurrentLocation()
        .subscribeBy(
          onError = {
            val errorMessage = "内部エラーが発生しました. 権限周りで発生しているようです"
            onError(
              errorMessage
            )
          },
          onComplete = {
            val errorMessage = "現在地を取得することができませんでした"
            onError(errorMessage)
          },
          onSuccess = {
            val currentLocation = CurrentLocationBindingModelConverter.convert(it)
            onSuccess(currentLocation)
          }
        )
    }
    // 現在地から指定して取得できるRepository の作成
  }
}