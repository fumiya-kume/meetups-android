package com.kuxu.api.entity

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Series(
    @Optional
    val id: Int = 0,
    @Optional
    val title: String? = "",
    @Optional
    val url: String = ""
)