package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.entity.NearMeetupEntity
import io.reactivex.Single

internal interface NearMeetupRepository {
  fun loadNearMeetupList(): Single<List<NearMeetupEntity>>
}