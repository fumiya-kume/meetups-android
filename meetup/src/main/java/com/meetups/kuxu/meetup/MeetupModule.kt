package com.meetups.kuxu.meetup

import com.meetups.kuxu.connpass_api.connpassApiModule
import com.meetups.kuxu.meetup.domain.domainModule
import com.meetups.kuxu.meetup.domain.service.serviceModule
import com.meetups.kuxu.meetup.ui.nearMeetupModule

val meetupModule = listOf(
  nearMeetupModule
) + domainModule