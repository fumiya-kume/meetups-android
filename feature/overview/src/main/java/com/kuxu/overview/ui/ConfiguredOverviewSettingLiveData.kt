package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import com.kuxu.overview.domain.ChoosePrefectureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

internal class ConfiguredOverviewSettingLiveData(
    private val choosePrefectureRepository: ChoosePrefectureRepository
) : LiveData<Boolean>(), CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

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