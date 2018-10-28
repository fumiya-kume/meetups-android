package com.meetups.kuxu.connpass_api.entity

import com.squareup.moshi.Json

public data class MeetupJson(
  @Json(name = "events") val events: List<EventJson>
)

public data class EventJson(
  @Json(name = "event_id") val eventId: Int,
  @Json(name = "title") val title: String,
  @Json(name = "event_url") val eventUrl: String,
  @Json(name = "lat") val lat: Double?,
  @Json(name = "lon") val lon: Double?
)