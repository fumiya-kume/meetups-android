package com.meetups.kuxu.meetup.domain.usecase

import androidx.room.Room
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val usecaseModule = module {
  factory { LoadNearMeetupUsecaseImpl(get(), get(), get()) as LoadNearMeetupUsecase }
  single { Room.databaseBuilder(androidContext(), MeetupDatabase::class.java, "meetup_database.db").build() }
  factory { get<MeetupDatabase>().meetupDao() }
}