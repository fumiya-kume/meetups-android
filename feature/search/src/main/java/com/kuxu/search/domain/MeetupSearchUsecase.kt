package com.kuxu.search.domain

import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.MeetupResultEntity

internal interface MeetupSearchUsecase {
    suspend fun execute(eventSearchQuery: EventSearchQuery): List<MeetupResultEntity>
}