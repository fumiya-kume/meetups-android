package com.kuxu.usersetting

import com.kuxu.usersetting.entity.PrefectureList

interface UserSetting {
    fun hasUserSetting(): Boolean
    fun loadChoosedPrefectureList(): PrefectureList
    fun storeChoosedPrefectureList(list: PrefectureList)
    fun loadLowEnergyMode(): Boolean
    fun storeLowEnergyMode(value: Boolean)
}