package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.service.CurrentLocationServiceImpl
import com.meetups.kuxu.meetup.infra.LoadNearMeetupUsecaseImpl
import com.meetups.kuxu.meetup.infra.MeetupRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

internal val domainModule = module {
  factory { MeetupRepositoryImpl(get()) as MeetupRepository }
  factory { CurrentLocationServiceImpl(androidApplication()) as CurrentLocationService }
  factory { LoadNearMeetupUsecaseImpl(get(), get()) as LoadNearMeetupUsecase }
}