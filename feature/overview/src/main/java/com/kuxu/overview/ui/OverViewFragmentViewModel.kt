package com.kuxu.overview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class OverViewFragmentViewModel(
    configuredOverviewSettingLiveDataFactory: ConfiguredOverviewSettingLiveDataFactory,
    private val meetupOverviewLiveDataFactory: MeetupOverviewLiveDataFactory
) : ViewModel() {

    val configuredOverviewSettingLiveData = configuredOverviewSettingLiveDataFactory.create()

    val meetupOverviewLiveData = meetupOverviewLiveDataFactory.create { exceptionHappen(it) }

    val isLoading = MutableLiveData<Boolean>()

    var exceptionHappen: (String) -> Unit = {}

    fun refreshMeetupList() {
        isLoading.value = true
        meetupOverviewLiveData.refreshMeetupOverview()
        isLoading.value = false
    }
}