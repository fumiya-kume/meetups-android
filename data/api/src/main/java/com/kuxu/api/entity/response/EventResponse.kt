package com.kuxu.api.entity.response

import com.kuxu.api.entity.Series

class EventResponse(
    val event_id: Int = 0,
    val title: String = "",
    val catch: String = "",
    val description: String? = "",
    val event_url: String = "",
    val hashTag: String? = "",
    val started_at: String = "",
    val ended_at: String = "",
    val limit: Int? = 0,
    val event_type: String = "participation",
    val series: Series? = Series(),
    val address: String? = "",
    val place: String? = "",
    val lat: String? = "0.0",
    val lon: String? = "0.0",
    val owner_id: Int = 0,
    val owner_nickname: String? = "",
    val owner_display_name: String? = "",
    val accepted: Int? = 0,
    val waiting: Int? = 0,
    val updated_at: String? = ""
)