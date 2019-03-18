package com.kuxu.search.infra

import com.kuxu.api.ConnpassClient
import com.kuxu.search.domain.MeetupRepository
import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.entity.convert

internal class MeetupRepositoryImpl : MeetupRepository {
    override suspend fun searchMeetup(eventSearchQuery: EventSearchQuery) =
        ConnpassClient.builder().keyword(eventSearchQuery.keyword).request().eventList.convert()

}