package com.kuxu.api

import com.kuxu.api.entity.Event

interface ConnpassClient {
    suspend fun loadAllEventList(): List<Event>
}