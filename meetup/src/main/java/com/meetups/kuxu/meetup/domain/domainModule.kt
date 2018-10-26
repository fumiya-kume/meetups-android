package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.infra.CurrentLocationServiceImpl
import com.meetups.kuxu.meetup.infra.NearMeetupRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

internal val domainModule = module {
  factory { NearMeetupRepositoryImpl(get()) as NearMeetupRepository }
  factory { CurrentLocationServiceImpl(androidApplication()) as CurrentLocationService }
}