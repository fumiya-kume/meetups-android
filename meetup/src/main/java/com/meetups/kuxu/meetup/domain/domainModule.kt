package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.infra.CurrentLocationServiceImpl
import com.meetups.kuxu.meetup.infra.MeetupRepositoryImpl
import com.meetups.kuxu.meetup.infra.SearchMeetupUsecaseImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

internal val domainModule = module {
  factory { MeetupRepositoryImpl(get()) as MeetupRepository }
  factory { CurrentLocationServiceImpl(androidApplication()) as CurrentLocationService }
  factory { SearchMeetupUsecaseImpl(get(), get()) as SearchMeetupUsecase }
}