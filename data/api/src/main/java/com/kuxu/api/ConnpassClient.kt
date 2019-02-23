package com.kuxu.api

import com.kuxu.api.entity.ConnpassAPIResponse
import com.kuxu.api.entity.serializer.ConnpassDateSerializer
import kotlinx.coroutines.Deferred

interface ConnpassClient {
    fun builder(): ConnpassClient
    fun eventStartDate(year: Int, month: Int): ConnpassClient
    fun eventStartDate(year: Int, month: Int, day: Int): ConnpassClient
    suspend fun request(): ConnpassAPIResponse
}