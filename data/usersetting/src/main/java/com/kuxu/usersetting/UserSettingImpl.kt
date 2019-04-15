package com.kuxu.usersetting

import android.content.Context
import com.kuxu.usersetting.entity.PrefectureList
import com.kuxu.usersetting.schema.UserSettings


class UserSettingImpl(
    private val context: Context
) : UserSetting {
    private val userSettingScheme = UserSettings.get(context)

    override fun hasUserSetting() =
        try {
            userSettingScheme.hasTargetPrefectureList() and userSettingScheme.targetPrefectureList.list.isNotEmpty()
        } catch (e: Exception) {
            userSettingScheme.removeTargetPrefectureList()
            userSettingScheme.putTargetPrefectureList(PrefectureList(emptyList()))
            false
        }

    override fun loadChoosedPrefectureList(): PrefectureList {
        try {
            return userSettingScheme.targetPrefectureList
        } catch (e: Exception) {
            userSettingScheme.targetPrefectureList = PrefectureList(emptyList())
            return PrefectureList(emptyList())
        }
    }

    override fun storeChoosedPrefectureList(list: PrefectureList) {
        userSettingScheme.targetPrefectureList = list
    }

    override fun loadLowEnergyMode(): Boolean {
        return userSettingScheme.enabledLowEnergyMode
    }

    override fun storeLowEnergyMode(value: Boolean) {
        userSettingScheme.enabledLowEnergyMode = value
    }
}
