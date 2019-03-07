package com.kuxu.overview.ui

import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.LoadMeetupOverviewListUsecase

internal class MeetupOverviewLiveDataFactory(
    private val choosePrefectureRepository: ChoosePrefectureRepository,
    private val loadMeetupOverviewListUsecase: LoadMeetupOverviewListUsecase
) {
    fun create(
        exceptionHappen: (message: String) -> Unit
    ) = MeetupOverviewLiveData(
        choosePrefectureRepository,
        loadMeetupOverviewListUsecase,
        exceptionHappen
    )
}