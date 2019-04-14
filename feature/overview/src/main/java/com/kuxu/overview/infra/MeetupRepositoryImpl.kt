package com.kuxu.overview.infra

import com.kuxu.api.ConnpassClient
import com.kuxu.api.entity.Event
import com.kuxu.overview.domain.MeetupRepository
import com.kuxu.overview.entity.MeetupEntity
import com.kuxu.overview.entity.Prefecture
import com.kuxu.overview.entity.convert

internal class MeetupRepositoryImpl(
    private val connpassClient: ConnpassClient
) : MeetupRepository {
    override suspend fun searchMeetupByPrefectureList(
        prefectureList: List<Prefecture>
    ): List<MeetupEntity> {
        return connpassClient
            .loadAllEventList()
            .filter { exitstPrefecture(it, prefectureList) }
            .convert()
    }

    private fun exitstPrefecture(event: Event, list: List<Prefecture>) =
        list.any { event.address?.contains(it.name) ?: false }
}