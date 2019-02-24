package com.kuxu.overview.domain

import com.kuxu.overview.entity.MeetupEntity

internal interface LoadMeetupOverviewListUsecase {
    suspend fun execute(): List<MeetupEntity>
}