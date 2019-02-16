package com.kuxu.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

internal class ConfiguredOverviewSettingViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}