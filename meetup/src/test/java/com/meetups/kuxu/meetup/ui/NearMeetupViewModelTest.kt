package com.meetups.kuxu.meetup.ui

import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.Assert.*

internal class NearMeetupViewModelTest: SubjectSpek<NearMeetupViewModel>({
  subject {
    NearMeetupViewModel()
  }

})