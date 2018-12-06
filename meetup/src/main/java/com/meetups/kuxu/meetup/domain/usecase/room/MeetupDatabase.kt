package com.meetups.kuxu.meetup.domain.usecase.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MeetupDao::class), version = 1)
abstract class MeetupDatabase : RoomDatabase() {
  abstract fun meetupDao(): MeetupDao
}