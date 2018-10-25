package com.meetups.kuxu.meetup.infra

import com.meetups.kuxu.connpass_api.MeetupListDataStore
import com.meetups.kuxu.meetup.domain.NearMeetupRepository
import com.meetups.kuxu.meetup.entity.NearMeetupEntity
import io.reactivex.Single

internal class NearMeetupRepositoryImpl(
  private val meetupListDataStore: MeetupListDataStore
) : NearMeetupRepository {
  override fun loadNearMeetupList(): Single<List<NearMeetupEntity>> =
    Single.just(meetupListDataStore.loadMeetupList().events.map { NearMeetupEntity(it.eventId, it.title, it.eventUrl) })

}
