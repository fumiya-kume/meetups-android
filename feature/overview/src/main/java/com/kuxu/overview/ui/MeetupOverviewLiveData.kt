package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

internal class MeetupOverviewLiveData(
    override val coroutineContext: CoroutineContext
) : LiveData<List<MeetupOverviewBindingModel>>(), CoroutineScope {
    override fun onActive() {
        super.onActive()
    }
}