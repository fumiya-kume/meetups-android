package com.meetups.kuxu.connpass_api.entity

import com.squareup.moshi.Json

public data class MeetupJson(
  @Json(name = "events") val events: List<EventJson>
)

public data class EventJson(
  val id: Int,
  val description: String,
  val event_url: String,
  val lat: Double,
  val lon: Double,
  val title: String
)