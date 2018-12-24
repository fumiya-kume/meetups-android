package com.meetups.kuxu.meetup.domain

import com.meetups.kuxu.meetup.domain.repository.repositoryModule
import com.meetups.kuxu.meetup.domain.service.serviceModule
import com.meetups.kuxu.meetup.domain.usecase.usecaseModule
import org.koin.dsl.module.module

internal val domainModule = listOf(
  repositoryModule,
  serviceModule,
  usecaseModule
)