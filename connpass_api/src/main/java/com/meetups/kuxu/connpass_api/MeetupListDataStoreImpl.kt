package com.meetups.kuxu.connpass_api

import com.github.kittinunf.fuel.httpGet
import com.meetups.kuxu.connpass_api.entity.MeetupJson
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking

internal class MeetupListDataStoreImpl : MeetupListDataStore {
  override fun loadMeetupList(): ReceiveChannel<MeetupJson> = GlobalScope.produce {
    runBlocking {
      "https://connpass.com/api/v1/event/?count=30".httpGet().awaitResultObject<MeetupJson>().await()
        .fold(
          success = {
            send(it)
          },
          failure = {
            throw it
          }
        )
    }
  }
}