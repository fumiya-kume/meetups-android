package com.kuxu.search.ui

import androidx.lifecycle.MutableLiveData
import com.kuxu.search.domain.MeetupSearchUsecase
import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.ui.bindingmodel.SearchResultBindingModel
import com.kuxu.search.ui.bindingmodel.convert
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class SearchResultLiveData(
    private val searchQuery: EventSearchQuery,
    private val meetupSearchUsecase: MeetupSearchUsecase,
    private val searchError: (String) -> Unit
) : MutableLiveData<List<SearchResultBindingModel>>(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onActive() {
        super.onActive()
        launch {
            try {
                postValue(
                    meetupSearchUsecase
                        .execute(searchQuery)
                        .convert()
                )
            } catch (e: Exception) {
                postValue(emptyList())
                searchError
            }
        }
    }

    override fun onInactive() {
        super.onInactive()

        job.cancel()
    }
}