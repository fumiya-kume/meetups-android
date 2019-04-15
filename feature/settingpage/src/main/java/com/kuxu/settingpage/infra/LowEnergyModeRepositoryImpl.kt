package com.kuxu.settingpage.infra

import com.kuxu.settingpage.domain.LowEnergyModeRepository
import com.kuxu.usersetting.UserSetting

internal class LowEnergyModeRepositoryImpl(
    private val userSetting: UserSetting
) : LowEnergyModeRepository {
    override suspend fun loadLowEnergyMode(): Boolean {
        return userSetting.loadLowEnergyMode()
    }

    override suspend fun storeLowEnergyMode(newValue: Boolean) {
        userSetting.storeLowEnergyMode(newValue)
    }
}