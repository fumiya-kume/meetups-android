package com.kuxu.api.entity

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConnpassAPIResponse(
    @SerialName("results_returned")
    val resultCount: Int,
    @SerialName("results_available")
    val resultAvaivable: Int,
    @SerialName("results_start")
    val searchStartPosition: Int,
    @SerialName("events")
    @Optional
    val eventList: List<Event> = emptyList()
)

