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
    val description: String = "",
    @SerialName("event_url")
    @Optional
    val eventUrl: String = "",
    @SerialName("hash_tag")
    @Optional
    val hashTag: String = "",
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
    val limit: Int = 0,
    @SerialName("event_type")
    val eventType: String,
    val series: Series,
    @Optional
    val address: String = "",
    @Optional
    val place: String = "",
    val lat: Double,
    val lon: Double,
    @SerialName("owner_id")
    val ownerId: Int,
    @SerialName("owner_nickname")
    val ownerNickname: String,
    @SerialName("owner_display_name")
    @Optional
    val ownerDisplayName: String = "",
    val accepted: Int,
    val waiting: Int,
    @SerialName("updated_at")
    @Serializable(with = ConnpassDateSerializer::class)
    @Optional
    val updatedAt: Date = Date()
)