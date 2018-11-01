package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.domain.CurrentLocationService
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnitPlatform::class)
internal class NearMeetupViewModelTest : SubjectSpek<NearMeetupViewModel>({

  val currentLocationService: CurrentLocationService by lazy {
    Mockito.mock(CurrentLocationService::class.java)
  }

  val meetupListLiveDataFactory: MeetupListLiveDataFactory by lazy {
    Mockito.mock(MeetupListLiveDataFactory::class.java)
  }

  val meetupListLiveData: MeetupListLiveData by lazy {
    Mockito.mock(MeetupListLiveData::class.java)
  }

  beforeGroup {
    `when`(meetupListLiveDataFactory.create()).thenReturn(meetupListLiveData)
  }

  subject {
    NearMeetupViewModel(meetupListLiveDataFactory)
  }
})