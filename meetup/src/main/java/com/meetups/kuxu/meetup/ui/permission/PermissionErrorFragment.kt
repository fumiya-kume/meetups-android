package com.meetups.kuxu.meetup.ui.permission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.meetups.kuxu.meetup.R
import com.meetups.kuxu.meetup.databinding.FragmentPermissionErrorBinding


class PermissionErrorFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val binding =
      FragmentPermissionErrorBinding.inflate(
        inflater,
        container,
        false
      )

    binding.navigateAppSettingMaterialButton.setOnClickListener {
      navigateSettingActivity()
    }

    binding.locationPermissionMaterialButton.setOnClickListener {
      activity?.let {
        ActivityCompat.requestPermissions(
          activity as Activity,
          arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
          0
        )

        view?.let {
          it.findNavController().navigate(R.id.action_global_nearMeetupOverviewFragment)
        }
      }
    }

    return binding.root
  }

  private fun navigateSettingActivity() {
    val intent = Intent().apply {
      this.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
      val packageName = context?.packageName ?: return
      val uri = Uri.fromParts(
        "package",
        packageName,
        null
      )
      this.data = uri
    }
    startActivity(intent)
  }
}
