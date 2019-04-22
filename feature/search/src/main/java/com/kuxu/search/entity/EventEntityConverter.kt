package com.kuxu.search.entity

import com.kuxu.api.entity.Event

internal fun Event.convert(): EventEntity =
    EventEntity(
        event_id,
        title,
        this.started_at,
        place,
        accepted,
        limit,
        event_url
    )

internal fun List<Event>.convert(): List<EventEntity> = this.map { it.convert() }