package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.MeetupEntity
import io.reactivex.Single

internal interface NearMeetupRepository {
  fun loadNearMeetupList(): Single<List<MeetupEntity>>
}