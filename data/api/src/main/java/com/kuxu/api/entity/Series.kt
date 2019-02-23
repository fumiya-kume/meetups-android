package com.kuxu.api.entity

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    val id: Int,
    @Optional
    val title: String = "",
    @Optional
    val url: String = ""
)