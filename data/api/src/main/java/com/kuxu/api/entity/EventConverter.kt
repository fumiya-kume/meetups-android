package com.kuxu.api.entity

import com.kuxu.api.entity.response.EventResponse
import java.text.SimpleDateFormat

private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

internal fun EventResponse.convert() = Event(
    this.event_id,
    this.title,
    this.catch,
    this.description ?: "",
    this.event_url,
    this.hashTag ?: "",
    dateFormat.parse(this.started_at),
    dateFormat.parse(this.ended_at),
    this.limit ?: 0,
    EventType.valueOf(this.event_type),
    this.series,
    this.address ?: "",
    this.place ?: "",
    this.lat?.toDoubleOrNull() ?: 0.0,
    this.lon?.toDoubleOrNull() ?: 0.0,
    this.owner_id,
    this.owner_nickname ?: "",
    this.owner_display_name ?: "",
    this.accepted ?: 0,
    this.waiting ?: 0,
    dateFormat.parse(this.updated_at)
)

internal fun List<EventResponse>?.convert(): List<Event> = this?.map { it.convert() } ?: emptyList()