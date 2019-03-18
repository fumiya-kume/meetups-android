package com.kuxu.search.ui

import com.kuxu.search.domain.MeetupSearchUsecase
import com.kuxu.search.entity.EventSearchQuery

internal class SearchResultLiveDataFactory(
    private val meetupSearchUsecase: MeetupSearchUsecase
) {
    fun create(
        eventSearchQuery: EventSearchQuery,
        searchError: (String) -> Unit
    ): SearchResultLiveData =
        SearchResultLiveData(
            eventSearchQuery,
            meetupSearchUsecase,
            searchError
        )
}