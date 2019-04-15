package com.kuxu.api.entity

import java.util.*


class Event(
    val event_id: Int = 0,
    val title: String = "",
    val catch: String = "",
    val description: String = "",
    val event_url: String = "",
    val hashTag: String = "",
    val started_at: Date = Date(),
    val ended_at: Date = Date(),
    val limit: Int = 0,
    val event_type: EventType = EventType.advertisement,
    val series: Series? = Series(),
    val address: String = "",
    val place: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val owner_id: Int = 0,
    val owner_nickname: String = "",
    val owner_display_name: String = "",
    val accepted: Int = 0,
    val waiting: Int = 0,
    val updated_at: Date = Date()
)