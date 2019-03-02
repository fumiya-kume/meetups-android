package com.kuxu.overview.ui

import androidx.lifecycle.MutableLiveData
import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.LoadMeetupOverviewListUsecase
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel
import com.kuxu.overview.ui.bindingmodel.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

internal class MeetupOverviewLiveData(
    private val choosePrefectureRepository: ChoosePrefectureRepository,
    private val loadMeetupOverviewListUsecase: LoadMeetupOverviewListUsecase
) : MutableLiveData<List<MeetupOverviewBindingModel>>(), CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onActive() {
        super.onActive()

        refreshMeetupOverview()
    }

    fun refreshMeetupOverview() {
        launch(Dispatchers.IO) {
            if (choosePrefectureRepository.hasConfigured()) {
                withContext(Dispatchers.Main) {
                    try {
                        value = loadMeetupOverviewListUsecase.execute()
                            .convert()
                    } catch (e: Exception) {
                        value = emptyList()
                    }
                }
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        coroutineContext.cancel()
    }
}