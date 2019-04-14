package com.kuxu.api.entity

import java.util.*


class Event(
    val event_id: Int = 0,
    val title: String = "",
    val catch: String = "",
    val description: String? = "",
    val event_url: String = "",
    val hashTag: String? = "",
    val startedAt: Date = Date(),
    val endedAt: Date = Date(),
    val limit: Int? = 0,
    val eventType: String = "participation",
    val series: Series? = Series(),
    val address: String? = "",
    val place: String? = "",
    val lat: String? = "0.0",
    val lon: String? = "0.0",
    val ownerId: Int = 0,
    val ownerNickname: String? = "",
    val ownerDisplayName: String? = "",
    val accepted: Int? = 0,
    val waiting: Int? = 0,
    val updatedAt: Date? = Date()
)