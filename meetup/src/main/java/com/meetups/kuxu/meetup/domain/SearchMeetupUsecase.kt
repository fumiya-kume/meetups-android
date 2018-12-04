package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.Deferred

internal interface SearchMeetupUsecase {
  fun execute(): Deferred<List<MeetupEntity>?>
}