package com.kuxu.search.entity

import java.util.*

internal data class MeetupResultEntity(
    val id: Int,
    val title: String?,
    val holdingDate: Date?,
    val holdingPlaceName: String?,
    val accept: Int,
    val limit: Int,
    val eventUrl: String
)