package com.kuxu.overview.domain

internal interface ConfiguredOverviewSettingRepository {
    suspend fun hasConfigured(): Boolean
}