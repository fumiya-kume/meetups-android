package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import com.kuxu.overview.domain.ChoosePrefectureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

internal class ConfiguredOverviewSettingLiveData(
    override val coroutineContext: CoroutineContext,
    private val choosePrefectureRepository: ChoosePrefectureRepository
) : LiveData<Boolean>(), CoroutineScope {

    override fun onActive() {
        super.onActive()
        runBlocking {
            try {
                value = choosePrefectureRepository.hasConfigured()
            } catch (e: Exception) {
                value = false
            }
        }
    }
}