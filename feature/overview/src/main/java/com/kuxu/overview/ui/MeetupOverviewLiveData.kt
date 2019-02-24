package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.LoadMeetupOverviewListUsecase
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel
import com.kuxu.overview.ui.bindingmodel.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class MeetupOverviewLiveData(
    override val coroutineContext: CoroutineContext,
    private val choosePrefectureRepository: ChoosePrefectureRepository,
    private val loadMeetupOverviewListUsecase: LoadMeetupOverviewListUsecase
) : LiveData<List<MeetupOverviewBindingModel>>(), CoroutineScope {

    override fun onActive() {
        super.onActive()

        launch(Dispatchers.IO) {
            if (choosePrefectureRepository.hasConfigured()) {
                try {
                    postValue(
                        loadMeetupOverviewListUsecase.execute()
                            .convert()
                    )
                } catch (e: Exception) {
                    postValue(
                        emptyList()
                    )
                }
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        coroutineContext.cancel()
    }
}