package com.kuxu.settingpage

import com.kuxu.settingpage.domain.LowEnergyModeRepository
import com.kuxu.settingpage.domain.PrefectureRepository
import com.kuxu.settingpage.domain.PrefectureSelectedUsecase
import com.kuxu.settingpage.infra.LowEnergyModeRepositoryImpl
import com.kuxu.settingpage.infra.PrefectureRepositoryImpl
import com.kuxu.settingpage.infra.PrefectureSelectedUsecaseImpl
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val settingPageModule = module {
    viewModel { RootSettingFragmentViewModel(get()) }

    factory { PrefectureRepositoryImpl(get(), get()) as PrefectureRepository }
    factory { PrefectureSelectedUsecaseImpl(get()) as PrefectureSelectedUsecase }
    factory { LowEnergyModeRepositoryImpl(get()) as LowEnergyModeRepository }
}