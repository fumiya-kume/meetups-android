package com.kuxu.overview.infra

import com.kuxu.api.ConnpassClient
import com.kuxu.api.entity.Event
import com.kuxu.overview.domain.MeetupRepository
import com.kuxu.overview.entity.MeetupEntity
import com.kuxu.overview.entity.Prefecture

internal class MeetupRepositoryImpl(
    private val connpassClient: ConnpassClient
) : MeetupRepository {
    override suspend fun searchMeetupByPrefectureList(
        prefectureList: List<Prefecture>
    ): List<MeetupEntity> {
        return connpassClient.builder()
            .request().eventList
            .filter { exitstPrefecture(it, prefectureList) }
            .map {
                MeetupEntity(
                    it.id,
                    it.title,
                    it.startedAt,
                    it.place,
                    it.accepted,
                    it.limit
                )
            }
    }

    private fun exitstPrefecture(event: Event, list: List<Prefecture>) = list.any { event.address.contains(it.name) }
}