package com.meetups.kuxu.meetup.domain.repository

import org.koin.dsl.module.module

val repositoryModule = module {
  factory { MeetupRepositoryImpl(get()) as MeetupRepository }
}