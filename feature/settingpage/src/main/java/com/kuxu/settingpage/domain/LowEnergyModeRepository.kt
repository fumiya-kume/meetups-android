package com.kuxu.settingpage.domain

internal interface LowEnergyModeRepository {
    suspend fun loadLowEnergyMode(): Boolean
    suspend fun storeLowEnergyMode(newValue: Boolean)
}