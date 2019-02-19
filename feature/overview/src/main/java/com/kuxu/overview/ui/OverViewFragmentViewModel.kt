package com.kuxu.overview.ui

import androidx.lifecycle.ViewModel

internal class OverViewFragmentViewModel (
    configuredOverviewSettingLiveDataFactory: ConfiguredOverviewSettingLiveDataFactory
) : ViewModel() {
    val configuredOverviewSettingLiveData =
        configuredOverviewSettingLiveDataFactory.create()
}