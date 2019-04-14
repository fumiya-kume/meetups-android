package com.kuxu.search.domain

import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.EventEntity

internal interface MeetupSearchUsecase {
    suspend fun execute(eventSearchQuery: EventSearchQuery): List<EventEntity>
}