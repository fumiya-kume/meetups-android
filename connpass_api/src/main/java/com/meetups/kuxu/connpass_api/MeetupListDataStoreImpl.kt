package com.meetups.kuxu.connpass_api

import com.github.kittinunf.fuel.httpGet
import com.meetups.kuxu.connpass_api.entity.MeetupJson
import kotlinx.coroutines.experimental.runBlocking

internal class MeetupListDataStoreImpl : MeetupListDataStore {
  override fun loadMeetupList(): MeetupJson =
    runBlocking {
      "https://connpass.com/api/v1/event/?count=30".httpGet().awaitResultObject<MeetupJson>().await()
        .fold(
          success = {
            return@fold it
          },
          failure = {
            throw it
          }
        )
    }

}