package com.kuxu.overview

import com.kuxu.overview.domain.ChoosePrefectureRepository
import com.kuxu.overview.domain.LoadMeetupOverviewListUsecase
import com.kuxu.overview.domain.MeetupRepository
import com.kuxu.overview.infra.ChoosePrefectureRepositoryImpl
import com.kuxu.overview.infra.MeetupRepositoryImpl
import com.kuxu.overview.infra.usecase.LoadMeetupOverviewListUsecaseImpl
import com.kuxu.overview.ui.ConfiguredOverviewSettingLiveDataFactory
import com.kuxu.overview.ui.MeetupOverviewLiveDataFactory
import com.kuxu.overview.ui.OverViewFragmentViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val overviewModule = module {

    viewModel { OverViewFragmentViewModel(get(), get()) }

    // LiveData Factory
    factory { ConfiguredOverviewSettingLiveDataFactory(get()) }
    factory { MeetupOverviewLiveDataFactory() }

    // Repository
    factory { ChoosePrefectureRepositoryImpl(get(), get()) as ChoosePrefectureRepository }
    factory { LoadMeetupOverviewListUsecaseImpl(get(), get()) as LoadMeetupOverviewListUsecase }
    factory { MeetupRepositoryImpl(get()) as MeetupRepository }
}