import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.meetups.kuxu.setting.R

internal class PreferenceRootFragment: PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    addPreferencesFromResource(R.xml.meetup_preferences)
  }
}