package com.meetups.kuxu.meetup.service

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

internal val serviceModule = module {
  factory { ConnpassEventPageViewerServiceImpl(androidApplication()) as ConnpassEventPageViewerService }
}