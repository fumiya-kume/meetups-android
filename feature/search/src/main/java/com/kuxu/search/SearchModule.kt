package com.kuxu.search

import com.kuxu.search.domain.MeetupRepository
import com.kuxu.search.domain.MeetupSearchUsecase
import com.kuxu.search.infra.MeetupRepositoryImpl
import com.kuxu.search.infra.usecase.MeetupSearchUsecaseImpl
import com.kuxu.search.ui.SearchResultFragmentViewModel
import com.kuxu.search.ui.SearchResultLiveDataFactory
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val searchModule = module {
    viewModel { SearchResultFragmentViewModel(get()) }

    factory { MeetupSearchUsecaseImpl(get()) as MeetupSearchUsecase }
    factory { MeetupRepositoryImpl() as MeetupRepository }

    factory { SearchResultLiveDataFactory(get()) }
}