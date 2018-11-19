package com.meetups.kuxu.meetup.ui.permission

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meetups.kuxu.meetup.databinding.FragmentPermissionErrorBinding


class PermissionErrorFragment : Fragment() {


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
      val intent = Intent()
      intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
      val packageName = context?.packageName ?: return@setOnClickListener
      val uri = Uri.fromParts(
        "package",
        packageName,
        null
      )
      intent.data = uri
      startActivity(intent)
    }

    return binding.root
  }
}
