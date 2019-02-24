package com.kuxu.overview.infra.usecase

import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.LoadMeetupOverviewListUsecase
import com.kuxu.overview.domain.MeetupRepository
import com.kuxu.overview.entity.MeetupEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class LoadMeetupOverviewListUsecaseImpl(
    private val choosePrefectureRepository: ChoosePrefectureRepository,
    private val meetupRepository: MeetupRepository
) : LoadMeetupOverviewListUsecase {
    override suspend fun execute(): List<MeetupEntity> =
        withContext(Dispatchers.IO) {
            meetupRepository.searchMeetupByPrefectureList(
                choosePrefectureRepository.loadChoosePrefecture()
            )
        }
}