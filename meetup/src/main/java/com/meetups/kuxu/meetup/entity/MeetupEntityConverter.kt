package com.meetups.kuxu.meetup.entity

import com.meetups.kuxu.connpass_api.entity.EventJson

internal object MeetupEntityConverter {
  fun convert(eventJson: EventJson) =
    MeetupEntity(
      eventJson.eventId,
      eventJson.title,
      eventJson.eventUrl,
      Int.MAX_VALUE,
      LocationEntity(
        eventJson.lat ?: 0.0,
        eventJson.lon ?: 0.0
      )
    )

  fun convert(eventJsonList: List<EventJson>) = eventJsonList.map { convert(it) }
}
