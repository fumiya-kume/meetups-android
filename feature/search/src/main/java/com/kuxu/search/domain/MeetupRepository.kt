package com.kuxu.search.domain

import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.EventEntity

internal interface MeetupRepository {
    suspend fun searchMeetup(eventSearchQuery: EventSearchQuery): List<EventEntity>
}