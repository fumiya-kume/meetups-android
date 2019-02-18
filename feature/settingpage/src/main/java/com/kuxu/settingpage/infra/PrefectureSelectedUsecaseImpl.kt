package com.kuxu.settingpage.infra

import com.kuxu.settingpage.domain.PrefectureSelectedUsecase
import com.kuxu.usersetting.UserSetting
import com.kuxu.usersetting.entity.PrefectureList

internal class PrefectureSelectedUsecaseImpl(
    private val userSetting: UserSetting
) : PrefectureSelectedUsecase {
    override suspend fun updateChooseStatus(
        id: Int,
        chooseStatus: Boolean
    ) {
        if (chooseStatus) {
            userSetting.storeChoosedPrefectureList(
                PrefectureList(
                    userSetting.loadChoosedPrefectureList().list.toMutableList().apply {
                        add(id)
                    }
                )
            )
        } else {
            userSetting.storeChoosedPrefectureList(
                PrefectureList(
                    userSetting.loadChoosedPrefectureList().list.filter { it != id }
                )
            )
        }
    }
}