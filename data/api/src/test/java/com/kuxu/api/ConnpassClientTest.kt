package com.kuxu.api

import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class ConnpassClientTest {
    @Test
    fun 設定何もなしでリクエストを送信してみる() = runBlocking {
        val client = ConnpassClient.builder()
        val response = client.request()
    }
}