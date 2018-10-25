package com.meetups.kuxu.meetup

import com.meetups.kuxu.connpass_api.connpassApiModule
import com.meetups.kuxu.meetup.ui.nearMeetupModule
import org.koin.dsl.module.module

val meetupModule = listOf(
  nearMeetupModule
) + connpassApiModule