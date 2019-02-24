package com.kuxu.overview.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

internal class OverViewFragmentViewModel(
    configuredOverviewSettingLiveDataFactory: ConfiguredOverviewSettingLiveDataFactory,
    private val meetupOverviewLiveDataFactory: MeetupOverviewLiveDataFactory
) : ViewModel(), CoroutineScope {
    val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val configuredOverviewSettingLiveData = configuredOverviewSettingLiveDataFactory.create(coroutineContext)
    val _meetupOverviewLiveData = meetupOverviewLiveDataFactory.create(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}