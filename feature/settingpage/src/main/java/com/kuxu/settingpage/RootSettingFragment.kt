package com.kuxu.settingpage


import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.kuxu.settingpage.dialog.ChoosePrefectureDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RootSettingFragment : PreferenceFragmentCompat() {

    private val rootSettingFragmentViewModel: RootSettingFragmentViewModel by viewModel()

    private val lowEnergySettingKey = "low_energy_mode"
    private val choosePreferenceKey = "choose_prefecture"

    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {
        setPreferencesFromResource(
            R.xml.root_preference,
            rootKey
        )
        val lowEnergySwitchPreference = findPreference(lowEnergySettingKey) as SwitchPreference
        GlobalScope.launch(Dispatchers.Main) {
            lowEnergySwitchPreference.isChecked = rootSettingFragmentViewModel.loadLowEnergyModeSetting()
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        preference?.let {
            when (it.key) {
                choosePreferenceKey -> ChoosePrefectureDialog().show(fragmentManager, tag)
                lowEnergySettingKey -> rootSettingFragmentViewModel.storeLowEnergyModeSetting((it as SwitchPreference).isChecked)
            }
        }
        return super.onPreferenceTreeClick(preference)
    }
}
