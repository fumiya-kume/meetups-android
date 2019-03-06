package com.kuxu.overview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class OverViewFragmentViewModel(
    configuredOverviewSettingLiveDataFactory: ConfiguredOverviewSettingLiveDataFactory,
    meetupOverviewLiveDataFactory: MeetupOverviewLiveDataFactory
) : ViewModel() {

    val configuredOverviewSettingLiveData = configuredOverviewSettingLiveDataFactory.create()

    val meetupOverviewLiveData = meetupOverviewLiveDataFactory.create { exceptionHappen(it) }

    val isLoading = MutableLiveData<Boolean>()

    // 例外が発生した時に ViewModel から View へ通知するためのラムダ
    var exceptionHappen: (String) -> Unit = {}

    fun refreshMeetupList() {
        isLoading.value = true
        meetupOverviewLiveData.refreshMeetupOverview()
    }
}