package com.kuxu.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuxu.search.entity.EventSearchQuery
import com.kuxu.search.ui.bindingmodel.SearchResultBindingModel

internal class SearchResultFragmentViewModel(
    private val searchResultLiveDataFactory: SearchResultLiveDataFactory
) : ViewModel() {

    val searchResultLiveData: LiveData<List<SearchResultBindingModel>>
        get() = _searchResultLiveData

    private var _searchResultLiveData: MutableLiveData<List<SearchResultBindingModel>> = MutableLiveData()

    var searchError: (String) -> Unit = {}

    fun search(eventSearchQuery: EventSearchQuery) {
        _searchResultLiveData =
            searchResultLiveDataFactory.create(
                eventSearchQuery,
                searchError
            )
    }
}