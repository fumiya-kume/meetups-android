package com.kuxu.settingpage


import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.kuxu.settingpage.dialog.ChoosePrefectureDialog

class RootSettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {
        setPreferencesFromResource(
            R.xml.root_preference,
            rootKey
        )
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        preference?.let {
            when (it.key) {
                "choose_prefecture" -> ChoosePrefectureDialog().show(fragmentManager, tag)
            }
        }

        return super.onPreferenceTreeClick(preference)
    }
}
