package com.meetups.kuxu.meetup.domain.usecase

import com.meetups.kuxu.meetup.domain.repository.MeetupRepository
import com.meetups.kuxu.meetup.domain.service.CurrentLocationService
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupDao
import com.meetups.kuxu.meetup.domain.usecase.room.MeetupRoomEntity
import com.meetups.kuxu.meetup.entity.LocationEntity
import com.meetups.kuxu.meetup.entity.MeetupEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.Assert.assertNotNull
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
internal class LoadNearMeetupUsecaseImplTest : SubjectSpek<LoadNearMeetupUsecaseImpl>({

  val meetupRepository = mock<MeetupRepository>()
  val currentLocationService = mock<CurrentLocationService>()
  val meetupDao = mock<MeetupDao>()


  subject {
    LoadNearMeetupUsecaseImpl(
      meetupRepository,
      currentLocationService,
      meetupDao
    )
  }

  given("読み込み時にローカルに保存されているデータが返され、そのあとに取得したデータが返ってくる") {
    on("読み込み時") {

      val dummySavedMeetupData = listOf<MeetupRoomEntity>(
        MeetupRoomEntity(0, "タイトル", "リンク", 0.0, lon = 0.0),
        MeetupRoomEntity(0, "タイトル", "リンク", 0.0, lon = 0.0),
        MeetupRoomEntity(0, "タイトル", "リンク", 0.0, lon = 0.0)
      )

      whenever(meetupDao.readAll()).thenReturn(dummySavedMeetupData)

      val dummyMeetupList = listOf(
        MeetupEntity(0, "タイトル", "リンク", 0, LocationEntity(0.0, 0.0)),
        MeetupEntity(0, "タイトル", "リンク", 0, LocationEntity(0.0, 0.0)),
        MeetupEntity(0, "タイトル", "リンク", 0, LocationEntity(0.0, 0.0))
      )

      whenever(meetupRepository.loadMeetupList()).then {
        return@then GlobalScope.async {
          dummyMeetupList
        }
      }
      whenever(currentLocationService.distanceTo(LocationEntity(0.0, 0.0))).then {
        return@then GlobalScope.async { 0 }
      }

      runBlocking {
        val receiveChannel = subject.execute()
        val firstItem = receiveChannel.receive()
        it("DBに保存されているものが読み込まれる") {
          verify(meetupDao).readAll()
          assertNotNull(firstItem)
          assertEquals(dummySavedMeetupData.count(), firstItem.count())
        }
        it("新しくデータを受け取れたときにDBの内容をクリアする") {
          verify(meetupDao).deleteAll()
        }
        it("取得したデータがデータベースに保存される") {
          verify(meetupDao).insertAll(Mockito.anyList())
        }
        val secondItem = receiveChannel.receive()
        it("API経由で取得したデータが返ってくる") {
          assertNotNull(secondItem)
          assertEquals(dummyMeetupList, secondItem)
        }
      }
    }
  }
})