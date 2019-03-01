package com.kuxu.api.entity

import com.kuxu.api.entity.serializer.ConnpassDateSerializer
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable()
data class Event(
    @SerialName("event_id")
    val id: Int,
    @SerialName("title")
    @Optional
    val title: String = "",
    @SerialName("catch")
    @Optional
    val catch: String = "",
    @SerialName("description")
    @Optional
    val description: String? = "",
    @SerialName("event_url")
    @Optional
    val eventUrl: String = "",
    @SerialName("hash_tag")
    @Optional
    val hashTag: String? = "",
    @SerialName("started_at")
    @Serializable(with = ConnpassDateSerializer::class)
    @Optional
    val startedAt: Date = Date(),
    @SerialName("ended_at")
    @Serializable(with = ConnpassDateSerializer::class)
    @Optional
    val endedAt: Date = Date(),
    @SerialName("limit")
    @Optional
    val limit: Int? = 0,
    @SerialName("event_type")
    val eventType: String,
    @SerialName("series")
    @Optional
    val series: Series? = Series(0, "", ""),
    @Optional
    val address: String? = "",
    @Optional
    val place: String? = "",
    @Optional
    val lat: Double? = 0.0,
    @Optional
    val lon: Double? = 0.0,
    @SerialName("owner_id")
    @Optional
    val ownerId: Int = 0,
    @SerialName("owner_nickname")
    @Optional
    val ownerNickname: String? = "",
    @SerialName("owner_display_name")
    @Optional
    val ownerDisplayName: String? = "",
    @Optional
    val accepted: Int? = 0,
    @Optional
    val waiting: Int? = 0,
    @SerialName("updated_at")
    @Serializable(with = ConnpassDateSerializer::class)
    @Optional
    val updatedAt: Date? = Date()
)