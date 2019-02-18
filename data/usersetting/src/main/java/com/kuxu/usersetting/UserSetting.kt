package com.kuxu.usersetting

import android.content.Context
import com.kuxu.usersetting.entity.PrefectureList
import com.kuxu.usersetting.schema.UserSettings


class UserSetting(
    private val context: Context
) {
    private val userSettingScheme = UserSettings.get(context)

    fun hasUserSetting() =
        userSettingScheme.hasTargetPrefectureList() and userSettingScheme.targetPrefectureList.list.isNotEmpty()

    fun loadChoosedPrefectureList(): PrefectureList {
        try {
            return userSettingScheme.targetPrefectureList
        } catch (e: Exception) {
            userSettingScheme.targetPrefectureList = PrefectureList(emptyList())
            return PrefectureList(emptyList())
        }
    }

    fun storeChoosedPrefectureList(list: PrefectureList) {
        userSettingScheme.targetPrefectureList = list
    }
}
