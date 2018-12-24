package com.meetups.kuxu.meetup.domain.repository

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.Deferred

internal interface MeetupRepository {
  fun loadMeetupList(): Deferred<List<MeetupEntity>?>
  fun searchMeetupList(keyword: String): Deferred<List<MeetupEntity>?>
}