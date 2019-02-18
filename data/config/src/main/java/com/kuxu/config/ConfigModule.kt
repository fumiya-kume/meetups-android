package com.kuxu.config

import org.koin.dsl.module.module

val configModule = module {
    single { PrefectureInfoRepository() }
}