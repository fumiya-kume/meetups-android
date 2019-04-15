package com.kuxu.settingpage

import androidx.lifecycle.ViewModel
import com.kuxu.settingpage.domain.LowEnergyModeRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal class RootSettingFragmentViewModel(
    private val lowEnergyModeRepository: LowEnergyModeRepository
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    suspend fun loadLowEnergyModeSetting(): Boolean {
        return withContext(Dispatchers.Default) { lowEnergyModeRepository.loadLowEnergyMode() }
    }

    fun storeLowEnergyModeSetting(newValue: Boolean) {
        launch { lowEnergyModeRepository.storeLowEnergyMode(newValue) }
    }
}