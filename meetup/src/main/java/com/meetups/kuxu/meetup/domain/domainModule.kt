package com.meetups.kuxu.meetup.domain

import androidx.room.Room
import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.repository.MeetupRepositoryImpl
import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.service.CurrentLocationServiceImpl
import com.meetups.kuxu.meetup.domain.usecase.LoadNearMeetupUsecase
import com.meetups.kuxu.meetup.domain.usecase.LoadNearMeetupUsecaseImpl
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

internal val domainModule = module {
  factory { MeetupRepositoryImpl(get()) as MeetupRepository }
  factory { CurrentLocationServiceImpl(androidApplication()) as CurrentLocationService }
  factory { LoadNearMeetupUsecaseImpl(get(), get(), get()) as LoadNearMeetupUsecase }
  single { Room.databaseBuilder(androidContext(), MeetupDatabase::class.java, "meetup_database.db").build() }
}