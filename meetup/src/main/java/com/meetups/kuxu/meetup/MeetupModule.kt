package com.meetups.kuxu.meetup

import com.meetups.kuxu.meetup.ui.nearMeetupModule
import org.koin.dsl.module.module

val meetupModule = module {
  this.factory {
    nearMeetupModule
  }
}