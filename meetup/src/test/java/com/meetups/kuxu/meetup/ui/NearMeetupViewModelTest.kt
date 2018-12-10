package com.meetups.kuxu.meetup.ui

import android.app.Application
import com.meetups.kuxu.meetup.domain.service.ConnpassEventPageViewerService
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.main.MeetupListLiveData
import com.meetups.kuxu.meetup.ui.main.MeetupListLiveDataFactory
import com.meetups.kuxu.meetup.ui.main.NearMeetupViewModel
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times

@RunWith(JUnitPlatform::class)
internal class NearMeetupViewModelTest : SubjectSpek<NearMeetupViewModel>({

  val meetupListLiveDataFactory: MeetupListLiveDataFactory by lazy {
    Mockito.mock(MeetupListLiveDataFactory::class.java)
  }

  val meetupListLiveData: MeetupListLiveData by lazy {
    Mockito.mock(MeetupListLiveData::class.java)
  }

  val connpassEventPageViewerService: ConnpassEventPageViewerService by lazy {
    Mockito.mock(ConnpassEventPageViewerService::class.java)
  }

  val application: Application by lazy {
    Mockito.mock(Application::class.java)
  }

  beforeGroup {
    `when`(meetupListLiveDataFactory.create()).thenReturn(meetupListLiveData)
  }

  subject {
    NearMeetupViewModel(
      meetupListLiveDataFactory,
      connpassEventPageViewerService,
      application
    )
  }

  given("イベントが選択されるとイベントページがブラウザで表示される") {
    on("イベントページが選択") {

      it("イベントページが表示される") {
        val tappedEventPageBindingModel = MeetupRowBindingModel(
          id = 0,
          meetupTitle = "タイトル",
          meetupUrl = "https://yahoo.co.jp",
          distance = "10km"
        )
        subject.showEventPage(tappedEventPageBindingModel)
        Mockito.verify(
          connpassEventPageViewerService,
          times(1)
        )
          .showEventPage(
            tappedEventPageBindingModel.meetupUrl
          )
      }

      it("URLが入ってない場合はイベントページが表示されない") {
        val tappedEventPageBindingModel = MeetupRowBindingModel(
          id = 0,
          meetupTitle = "タイトル",
          meetupUrl = "",
          distance = "10km"
        )
        subject.showEventPage(tappedEventPageBindingModel)
        Mockito.verify(
          connpassEventPageViewerService,
          times(0)
        )
          .showEventPage(
            tappedEventPageBindingModel.meetupUrl
          )
      }
    }
  }
})