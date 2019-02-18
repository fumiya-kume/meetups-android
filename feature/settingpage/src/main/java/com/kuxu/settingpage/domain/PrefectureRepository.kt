package com.kuxu.settingpage.domain

import com.kuxu.entity.Prefecture
import com.kuxu.settingpage.bindingModel.PrefectureBindingModel

internal interface PrefectureRepository {
    suspend fun loadPrefectureList(): List<PrefectureBindingModel>
}