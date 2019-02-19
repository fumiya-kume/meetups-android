package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import com.kuxu.overview.domain.ConfiguredOverviewSettingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class ConfiguredOverviewSettingLiveData(
    private val configuredOverviewSettingRepository: ConfiguredOverviewSettingRepository
) : LiveData<Boolean>(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onActive() {
        super.onActive()
        launch {
            value = configuredOverviewSettingRepository.hasConfigured()
        }
    }
}