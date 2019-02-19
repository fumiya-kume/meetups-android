package com.kuxu.overview.ui

import com.kuxu.overview.domain.ConfiguredOverviewSettingRepository

internal class ConfiguredOverviewSettingLiveDataFactory(
    private val configuredOverviewSettingRepository: ConfiguredOverviewSettingRepository
) {
    fun create() = ConfiguredOverviewSettingLiveData(
        configuredOverviewSettingRepository
    )
}