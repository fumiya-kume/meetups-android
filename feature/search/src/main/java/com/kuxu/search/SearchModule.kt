package com.kuxu.search

import com.kuxu.search.ui.SearchResultFragmentViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val searchModule = module {
    viewModel { SearchResultFragmentViewModel(get()) }
}