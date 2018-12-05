package com.meetups.kuxu.meetup.entity

import com.meetups.kuxu.connpass_api.entity.EventJson

internal object MeetupEntityConverter {
  fun convert(eventJson: EventJson) =
    MeetupEntity(
      eventJson.id,
      eventJson.title,
      eventJson. event_url,
      Int.MAX_VALUE,
      LocationEntity(
        eventJson.lat,
        eventJson.lon
      )
    )

  fun convert(eventJsonList: List<EventJson>) = eventJsonList.map { convert(it) }
}
