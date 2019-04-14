package com.kuxu.api

import org.koin.dsl.module.module

val apiModule = module {
    factory { ConnpassClientImpl() as ConnpassClient }
}