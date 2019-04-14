package com.kuxu.search.infra

import com.kuxu.api.ConnpassClient
import com.kuxu.search.domain.MeetupRepository
import com.kuxu.search.entity.EventEntity
import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.convert
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

internal class MeetupRepositoryImpl(
    private val connpassClient: ConnpassClient
) : MeetupRepository {
    override suspend fun searchMeetup(eventSearchQuery: EventSearchQuery): List<EventEntity> =
        GlobalScope.async { connpassClient.loadAllEventList().convert() }.await()
}