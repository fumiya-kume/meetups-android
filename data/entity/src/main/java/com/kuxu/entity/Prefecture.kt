package com.kuxu.entity

import kotlinx.serialization.Serializable


@Serializable
data class Prefecture(
    val id: Int,
    val name: String
)