package com.kuxu.settingpage.domain

internal interface PrefectureSelectedUsecase {
    suspend fun updateChooseStatus(id: Int, chooseStatus: Boolean)
}