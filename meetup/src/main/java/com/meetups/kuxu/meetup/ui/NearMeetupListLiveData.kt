package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.LiveData
import com.meetups.kuxu.meetup.domain.NearMeetupRepository
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.meetupRowBindingModelConverter
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

internal class NearMeetupListLiveData(
  private val nearMeetupRepository: NearMeetupRepository
) : LiveData<List<MeetupRowBindingModel>>() {
  override fun onActive() {
    super.onActive()

    GlobalScope.launch {
      nearMeetupRepository.loadNearMeetupList()
        .observeOn(Schedulers.io())
        .subscribeBy(
          onSuccess = {
            value = meetupRowBindingModelConverter.convert(it)
          },
          onError = {
            value = emptyList()
          }
        )
    }
  }
}
