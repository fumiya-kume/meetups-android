package com.kuxu.overview.infra

import com.kuxu.overview.domain.ConfiguredOverviewSettingRepository
import com.kuxu.usersetting.UserSetting

internal class ConfiguredOverviewSettingRepositoryImpl(
    private val userSetting: UserSetting
) : ConfiguredOverviewSettingRepository {
    override suspend fun hasConfigured(): Boolean {
        try {
            return userSetting.hasUserSetting()
        } catch (e: Exception) {
            // Logger 処理
            return false
        }
    }
}