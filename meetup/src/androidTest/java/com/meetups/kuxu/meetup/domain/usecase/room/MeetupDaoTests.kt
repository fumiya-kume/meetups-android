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
      scenario("アイテムを一つ書き込む") {
        val meetupRoomEntity = MeetupRoomEntity()
        dao.insert(meetupRoomEntity)
        dao.readAll().count().shouldBe(1)
      }
    }
    feature("データベースからアイテムを一つ消去することができる") {
      scenario("データベースからアイテムを一つ消去するとデータベースが空になる") {
        val meetupRoomEntity = MeetupRoomEntity()
        dao.insert(meetupRoomEntity)
        dao.readAll().count().shouldBe(1)
        dao.deleteAll()
        dao.readAll().count().shouldBe(0)
      }

      scenario("データベースからアイテムを消去するとデータベースが空になる") {
        val meetupRoomEntityList = (1..10).map { MeetupRoomEntity(id = it) }
        meetupRoomEntityList.forEach { item -> dao.insert(item) }
        dao.readAll().count().shouldBe(meetupRoomEntityList.count())
        dao.deleteAll()
        dao.readAll().count().shouldBe(0)
      }
    }
  }
}