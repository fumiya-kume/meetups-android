package com.kuxu.overview.entity

import java.util.*

internal data class MeetupEntity(
    val id: Int,
    val title: String?,
    val holdingDate: Date?,
    val holdingPlaceName: String?,
    val accept: Int,
    val limit: Int
)