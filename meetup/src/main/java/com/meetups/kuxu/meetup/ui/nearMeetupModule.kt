package com.meetups.kuxu.meetup.ui

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

internal val nearMeetupModule = module {
  viewModel { NearMeetupViewModel(get()) }
}