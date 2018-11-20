package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.ui.main.MeetupListLiveDataFactory
import com.meetups.kuxu.meetup.ui.main.NearMeetupViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

internal val nearMeetupModule = module {
  factory { MeetupListLiveDataFactory(get(), get()) }
  viewModel { NearMeetupViewModel(get(), get(), androidApplication()) }
}