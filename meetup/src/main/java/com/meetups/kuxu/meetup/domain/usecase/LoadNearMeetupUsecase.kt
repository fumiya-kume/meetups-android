package com.meetups.kuxu.meetup.domain.usecase

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.Deferred

internal interface LoadNearMeetupUsecase {
  fun execute(): Deferred<List<MeetupEntity>?>
}