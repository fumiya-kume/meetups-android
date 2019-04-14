package com.kuxu.overview.entity

import com.kuxu.api.entity.Event

internal fun Event.convert(): MeetupEntity = MeetupEntity(
    this.event_id,
    this.title,
    this.startedAt,
    this.place,
    this.accepted ?: 0,
    this.limit ?: 0,
    this.event_url
)

internal fun List<Event>.convert(): List<MeetupEntity> = this.map { it.convert() }