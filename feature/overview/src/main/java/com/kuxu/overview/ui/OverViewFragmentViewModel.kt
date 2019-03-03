package com.kuxu.overview.ui

import androidx.lifecycle.ViewModel

internal class OverViewFragmentViewModel(
    configuredOverviewSettingLiveDataFactory: ConfiguredOverviewSettingLiveDataFactory,
    private val meetupOverviewLiveDataFactory: MeetupOverviewLiveDataFactory
) : ViewModel() {

    val configuredOverviewSettingLiveData = configuredOverviewSettingLiveDataFactory.create()

    val meetupOverviewLiveData = meetupOverviewLiveDataFactory.create()

    var networkErrorCallback: () -> Unit = {}

    fun refreshMeetupList() {
        try {
            meetupOverviewLiveData.refreshMeetupOverview()
        } catch (e: Exception) {
            networkErrorCallback()
        }
    }
}