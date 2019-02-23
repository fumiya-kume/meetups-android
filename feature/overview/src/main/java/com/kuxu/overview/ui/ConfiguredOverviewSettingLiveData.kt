package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import com.kuxu.overview.domain.ConfiguredOverviewSettingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class ConfiguredOverviewSettingLiveData(
    override val coroutineContext: CoroutineContext,
    private val configuredOverviewSettingRepository: ConfiguredOverviewSettingRepository
) : LiveData<Boolean>(), CoroutineScope {

    override fun onActive() {
        super.onActive()
        launch {
            value = configuredOverviewSettingRepository.hasConfigured()
        }
    }
}