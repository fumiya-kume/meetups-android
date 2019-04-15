package com.kuxu.meetups

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.kuxu.api.apiModule
import com.kuxu.config.configModule
import com.kuxu.overview.overviewModule
import com.kuxu.search.searchModule
import com.kuxu.settingpage.settingPageModule
import com.kuxu.usersetting.userSettingModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(
            this,
            FirebaseOptions
                .Builder()
                .setApplicationId(BuildConfig.firebaseApplicationId)
                .setApiKey(BuildConfig.firebaseAPIKKey)
                .setProjectId("meetups-kuxu")
                .build()
        )

        startKoin(
            applicationContext,
            listOf(
                apiModule,
                configModule,
                userSettingModule,
                overviewModule,
                settingPageModule,
                searchModule
            )
        )
    }
}