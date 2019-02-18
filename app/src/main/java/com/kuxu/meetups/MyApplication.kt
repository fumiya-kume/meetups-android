package com.kuxu.meetups

import android.app.Application
import com.kuxu.config.configModule
import com.kuxu.overview.overviewModule
import com.kuxu.settingpage.settingPageModule
import com.kuxu.usersetting.userSettingModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            applicationContext,
            listOf(
                configModule,
                userSettingModule,
                overviewModule,
                settingPageModule
            )
        )
    }
}