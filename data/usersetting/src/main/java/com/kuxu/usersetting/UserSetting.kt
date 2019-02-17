package com.kuxu.usersetting

import android.content.Context
import com.kuxu.usersetting.schema.UserSettings


class UserSetting(
    private val context: Context
) {
    private val userSettingScheme = UserSettings.get(context)

    fun hasUserSetting() = userSettingScheme.hasTargetPrefectureList()
}
