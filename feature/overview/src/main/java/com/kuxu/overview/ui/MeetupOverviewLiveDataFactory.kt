package com.kuxu.overview.ui

import kotlin.coroutines.CoroutineContext

internal class MeetupOverviewLiveDataFactory() {
    fun create(
        coroutineContext: CoroutineContext
    ) = MeetupOverviewLiveData(coroutineContext)
}