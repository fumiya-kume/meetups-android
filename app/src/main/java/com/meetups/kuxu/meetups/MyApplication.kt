package com.meetups.kuxu.meetups

import android.app.Application
import com.meetups.kuxu.meetup.meetupModule
import org.koin.android.ext.android.startKoin

internal class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin(
      this,
      listOf(
        meetupModule
      )
    )
  }
}