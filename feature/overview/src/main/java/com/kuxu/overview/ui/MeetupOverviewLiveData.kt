package com.kuxu.overview.ui

import androidx.lifecycle.LiveData
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
    private val loadMeetupOverviewListUsecase: LoadMeetupOverviewListUsecase,
    var exceptionHappen: ((message: String) -> Unit)
) : LiveData<List<MeetupOverviewBindingModel>>(), CoroutineScope {

    val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onActive() {
        super.onActive()

        refreshMeetupOverview()
    }

    fun refreshMeetupOverview() {
        launch(Dispatchers.IO) {
            if (choosePrefectureRepository.hasConfigured()) {
                try {
                    postValue(
                        loadMeetupOverviewListUsecase
                            .execute()
                            .convert()
                    )
                } catch (e: Exception) {
                    postValue(
                        emptyList()
                    )
                    withContext(Dispatchers.Main) {
                        exceptionHappen("例外が発生しました")
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