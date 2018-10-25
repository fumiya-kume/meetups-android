package com.meetups.kuxu.connpass_api

import org.koin.dsl.module.module

private val datastoreModule = module {
  factory { MeetupListDataStoreImpl() as MeetupListDataStore }
}

public val connpassApiModule = listOf(
  datastoreModule
)