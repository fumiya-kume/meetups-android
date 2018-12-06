package com.meetups.kuxu.meetup.domain.usecase.room

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import io.kotlintest.TestCaseContext
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec


internal class MeetupDaoTests : FeatureSpec() {

  val context = InstrumentationRegistry.getInstrumentation().targetContext

  val database = Room.inMemoryDatabaseBuilder(context, MeetupDatabase::class.java).build()
  val dao = database.meetupDao()

  override fun interceptTestCase(context: TestCaseContext, test: () -> Unit) {

    database.clearAllTables()

    test()

  }

  init {

    feature("データベースに書き込みをすることができる") {
      scenario("最初は何も書き込まれていない") {
        dao.readAll().count().shouldBe(0)
      }
    }
  }
}