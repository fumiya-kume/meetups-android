package com.kuxu.overview

import com.kuxu.overview.domain.ConfiguredOverviewSettingRepository
import com.kuxu.overview.infra.ConfiguredOverviewSettingRepositoryImpl
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val overviewModule = module {
    viewModel { OverViewFragmentViewModel(get()) }
    factory { ConfiguredOverviewSettingLiveDataFactory(get()) }
    factory { ConfiguredOverviewSettingRepositoryImpl(get()) as ConfiguredOverviewSettingRepository }
}