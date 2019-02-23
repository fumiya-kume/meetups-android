package com.kuxu.overview.ui

import com.kuxu.overview.domain.ConfiguredOverviewSettingRepository
import kotlin.coroutines.CoroutineContext

internal class ConfiguredOverviewSettingLiveDataFactory(
    private val configuredOverviewSettingRepository: ConfiguredOverviewSettingRepository
) {
    fun create(
        coroutineContext: CoroutineContext
    ) = ConfiguredOverviewSettingLiveData(
        coroutineContext,
        configuredOverviewSettingRepository
    )
}