package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.domain.NearMeetupRepository
import com.meetups.kuxu.meetup.infra.NearMeetupRepositoryImpl
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

internal val nearMeetupModule = module {
  factory { NearMeetupRepositoryImpl(get()) as NearMeetupRepository }
  factory { NearMeetupListLiveDataFactory(get()) }
  viewModel { NearMeetupViewModel(get()) }
}