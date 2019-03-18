package com.kuxu.search.entity

import com.kuxu.api.entity.Event

internal fun Event.convert(): MeetupResultEntity =
    MeetupResultEntity(
        id,
        title,
        this.startedAt,
        place,
        accepted ?: 0,
        limit ?: 0,
        eventUrl
    )

internal fun List<Event>.convert(): List<MeetupResultEntity> = this.map { it.convert() }