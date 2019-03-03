package com.kuxu.overview.domain

import com.kuxu.overview.entity.MeetupEntity
import com.kuxu.overview.entity.Prefecture

internal interface MeetupRepository {
    suspend fun searchMeetupByPrefectureList(prefectureList: List<Prefecture>): List<MeetupEntity>
}