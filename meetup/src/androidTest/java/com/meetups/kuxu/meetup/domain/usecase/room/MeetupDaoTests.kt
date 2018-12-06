package com.meetups.kuxu.meetup.domain.usecase.room

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MeetupDaoTests {
  val context = InstrumentationRegistry.getInstrumentation().context
  lateinit var database: MeetupDatabase
  @Before
  fun BeforeTests() {
    database = Room.inMemoryDatabaseBuilder(context, MeetupDatabase::class.java).allowMainThreadQueries().build()
  }

  @Test
  fun fist() {
    val snapShot = database.meetupDao().readAll()
    Assert.assertNotNull(snapShot)
  }
}