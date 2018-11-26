package com.meetups.kuxu.connpass_api

import awaitStringResponse
import com.github.kittinunf.fuel.httpGet
import com.meetups.kuxu.connpass_api.entity.EventJson
import com.meetups.kuxu.connpass_api.entity.MeetupJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.runBlocking
import java.lang.reflect.Type

internal class MeetupListDataStoreImpl : MeetupListDataStore {
  override fun loadMeetupList(): ReceiveChannel<List<EventJson>> = GlobalScope.produce {
    runBlocking {
      "https://meetups-api.azurewebsites.net/events?count=100"
        .httpGet()
        .awaitStringResponse()
        .third
        .fold(
          success = {
            val type: Type = Types.newParameterizedType(List::class.javaObjectType, EventJson::class.javaObjectType)
            val jsonAdapter: JsonAdapter<List<EventJson>> = Moshi.Builder().build().adapter(type)
            val result = jsonAdapter.fromJson(it)
            result?.let {
              send(it)
            }
          },
          failure = {
            throw it
          }
        )
    }
  }

  override fun searchMeetupList(keyword: String): ReceiveChannel<MeetupJson> = GlobalScope.produce {
    runBlocking {
      "https://connpass.com/api/v1/event/?count=30&keyword=${keyword}".httpGet().awaitResultObject<MeetupJson>().await()
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