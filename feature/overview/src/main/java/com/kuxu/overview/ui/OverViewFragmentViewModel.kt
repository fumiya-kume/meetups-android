package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel
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

    var meetupOverviewLiveData: LiveData<List<MeetupOverviewBindingModel>> =
        MutableLiveData<List<MeetupOverviewBindingModel>>()

    fun loadmeetupOverView() {
        meetupOverviewLiveData = meetupOverviewLiveDataFactory.create(coroutineContext)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}