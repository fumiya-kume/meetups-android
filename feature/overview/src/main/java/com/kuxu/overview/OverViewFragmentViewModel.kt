package com.kuxu.overview

import androidx.lifecycle.ViewModel

internal class OverViewFragmentViewModel(
    private val configuredOverviewSettingLiveDataFactory: ConfiguredOverviewSettingLiveDataFactory
) : ViewModel() {
    val configuredOverviewSettingLiveData =
        configuredOverviewSettingLiveDataFactory.create()
}