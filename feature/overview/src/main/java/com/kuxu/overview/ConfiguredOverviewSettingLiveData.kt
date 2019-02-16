package com.kuxu.overview

import androidx.lifecycle.LiveData

internal class ConfiguredOverviewSettingLiveData() : LiveData<Boolean>() {
    override fun onActive() {
        super.onActive()
        value = false
    }
}