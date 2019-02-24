package com.kuxu.overview.ui

import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.LoadMeetupOverviewListUsecase
import kotlin.coroutines.CoroutineContext

internal class MeetupOverviewLiveDataFactory(
    private val choosePrefectureRepository: ChoosePrefectureRepository,
    private val loadMeetupOverviewListUsecase: LoadMeetupOverviewListUsecase
) {
    fun create(
        coroutineContext: CoroutineContext
    ) = MeetupOverviewLiveData(
        coroutineContext,
        choosePrefectureRepository,
        loadMeetupOverviewListUsecase
    )
}