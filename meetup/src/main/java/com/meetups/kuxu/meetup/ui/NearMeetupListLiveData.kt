package com.meetups.kuxu.meetup.ui

import androidx.lifecycle.LiveData
import com.meetups.kuxu.meetup.domain.NearMeetupRepository
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.meetupRowBindingModelConverter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

internal class NearMeetupListLiveData(
  private val nearMeetupRepository: NearMeetupRepository
) : LiveData<List<MeetupRowBindingModel>>() {
  override fun onActive() {
    super.onActive()

    GlobalScope.launch(Dispatchers.IO) {
      nearMeetupRepository.loadNearMeetupList()
        .observeOn(AndroidSchedulers.mainThread())
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
