package com.meetups.kuxu.meetup.domain.usecase

import com.meetups.kuxu.meetup.entity.MeetupEntity
import kotlinx.coroutines.channels.ReceiveChannel

internal interface LoadNearMeetupUsecase {
  fun execute(): ReceiveChannel<List<MeetupEntity>>
}