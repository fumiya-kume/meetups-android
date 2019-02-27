package com.kuxu.api

import com.github.kittinunf.fuel.Fuel
import com.kuxu.api.entity.ConnpassAPIResponse
import com.kuxu.api.exception.ServerMaintenanceException
import kotlinx.serialization.json.Json
import java.util.Collections.emptyMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ConnpassClient private constructor() {
    // 将来的にlocalhost から取得することを強制できるようにするためにBuilder Pattern を導入している
    companion object {
        fun builder(): ConnpassClient = ConnpassClient()
    }

    suspend fun request(): ConnpassAPIResponse = suspendCoroutine {
        val host = "https://connpass.com"
        val path = "/api/v1/event/?"
        val query = queryString()
        val requestUrl = host + path + query

        val result =
            Fuel
                .get(requestUrl)
                .header("User-Agent" to "android-meeetups")
                .responseString()


        if (result.second.statusCode == 503) {
            throw ServerMaintenanceException()
        }

        val string = result.third.get()

        it.resume(
            Json.parse(ConnpassAPIResponse.serializer(), result.third.get())
        )
    }

    private val queryList: MutableMap<ConnpassQueryKind, String> = emptyMap()

    private fun queryString() =
        queryList
            .map { "${it.key.queryName}=${it.value}&" }
            .fold("") { partQuery, acc -> partQuery + acc }

    fun eventStartDate(year: Int, month: Int): ConnpassClient {
        queryList.put(
            ConnpassQueryKind.HoldingDueYM,
            year.toString() + month.toString()
        )
        return this
    }

    fun eventStartDate(year: Int, month: Int, day: Int): ConnpassClient {
        queryList.put(
            ConnpassQueryKind.HoldingDueYMD,
            year.toString() + month.toString() + day.toString()
        )
        return this
    }

    fun keyword(keyword: String): ConnpassClient {
        queryList
            .put(
                ConnpassQueryKind.Keyword,
                keyword
            )
        return this
    }

    fun keywordOr(keyword: String): ConnpassClient {
        queryList
            .put(
                ConnpassQueryKind.KeywordOr,
                keyword
            )
        return this
    }
}

internal enum class ConnpassQueryKind(val queryName: String) {
    HoldingDueYM("ym"),
    HoldingDueYMD("ymd"),
    Keyword("keyword"),
    KeywordOr("keyword_or")
}