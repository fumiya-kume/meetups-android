package com.kuxu.usersetting

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val userSettingModule = module {
    factory { UserSettingImpl(androidContext()) as UserSetting }
}