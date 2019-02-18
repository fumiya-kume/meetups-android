package com.kuxu.settingpage

import com.kuxu.settingpage.domain.PrefectureRepository
import com.kuxu.settingpage.domain.PrefectureSelectedUsecase
import com.kuxu.settingpage.infra.PrefectureRepositoryImpl
import com.kuxu.settingpage.infra.PrefectureSelectedUsecaseImpl
import org.koin.dsl.module.module

val settingPageModule = module {
    factory { PrefectureRepositoryImpl(get(), get()) as PrefectureRepository }
    factory { PrefectureSelectedUsecaseImpl(get()) as PrefectureSelectedUsecase }
}