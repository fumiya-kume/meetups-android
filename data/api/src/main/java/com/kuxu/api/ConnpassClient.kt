package com.kuxu.api

import com.kuxu.api.entity.ConnpassAPIResponse

interface ConnpassClient {
    fun builder(): ConnpassClient
    fun eventStartDate(year: Int, month: Int): ConnpassClient
    fun eventStartDate(year: Int, month: Int, day: Int): ConnpassClient
    fun keyword(keyword: String): ConnpassClient
    fun keywordOr(keyword: String): ConnpassClient
    suspend fun request(): ConnpassAPIResponse
}