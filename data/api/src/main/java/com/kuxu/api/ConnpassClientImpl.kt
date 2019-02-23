package com.kuxu.api

import com.github.kittinunf.fuel.Fuel
import com.kuxu.api.entity.ConnpassAPIResponse
import com.kuxu.api.exception.ServerMaintenanceException
import kotlinx.serialization.json.Json
import java.util.Collections.emptyMap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class ConnpassClientImpl private constructor() : ConnpassClient {
    // 将来的にlocalhost から取得することを強制できるようにするためにBuilder Pattern を導入している
    override fun builder(): ConnpassClient = ConnpassClientImpl()

    override suspend fun request(): ConnpassAPIResponse = suspendCoroutine {
        val host = "https://connpass.com"
        val path = "/about/api/?"
        val query = queryString()
        val response = Fuel.get(host + path + query).response().second
        if (response.statusCode == 503) {
            throw ServerMaintenanceException()
        }
        it.resume(
            Json.parse(ConnpassAPIResponse.serializer(), response.responseMessage)
        )
    }

    private val queryList: MutableMap<ConnpassQueryKind, String> = emptyMap()

    private fun queryString() =
        queryList
            .map { "${it.key.queryName}=${it.value}&" }
            .fold("") { partQuery, acc -> partQuery + acc }

    override fun eventStartDate(year: Int, month: Int): ConnpassClient {
        queryList.put(
            ConnpassQueryKind.HoldingDueYM,
            year.toString() + month.toString()
        )
        return this
    }

    override fun eventStartDate(year: Int, month: Int, day: Int): ConnpassClient {
        queryList.put(
            ConnpassQueryKind.HoldingDueYMD,
            year.toString() + month.toString() + day.toString()
        )
        return this
    }

    override fun keyword(keyword: String): ConnpassClient {
        queryList
            .put(
                ConnpassQueryKind.Keyword,
                keyword
            )
        return this
    }

    override fun keywordOr(keyword: String): ConnpassClient {
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