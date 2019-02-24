package com.kuxu.overview.ui

import com.kuxu.overview.domain.ChoosePrefectureRepository
import kotlin.coroutines.CoroutineContext

internal class ConfiguredOverviewSettingLiveDataFactory(
    private val choosePrefectureRepository: ChoosePrefectureRepository
) {
    fun create(
        coroutineContext: CoroutineContext
    ) = ConfiguredOverviewSettingLiveData(
        coroutineContext,
        choosePrefectureRepository
    )
}