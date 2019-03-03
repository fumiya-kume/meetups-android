package com.kuxu.overview.ui

import com.kuxu.overview.domain.ChoosePrefectureRepository

internal class ConfiguredOverviewSettingLiveDataFactory(
    private val choosePrefectureRepository: ChoosePrefectureRepository
) {
    fun create(
    ) = ConfiguredOverviewSettingLiveData(
        choosePrefectureRepository
    )
}