package com.kuxu.search.infra.usecase

import com.kuxu.search.domain.MeetupRepository
import com.kuxu.search.domain.MeetupSearchUsecase
import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.EventEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MeetupSearchUsecaseImpl(
    private val meetupRepository: MeetupRepository
) : MeetupSearchUsecase {
    override suspend fun execute(eventSearchQuery: EventSearchQuery): List<EventEntity> =
        withContext(Dispatchers.IO) {
            meetupRepository.searchMeetup(eventSearchQuery)
        }

}