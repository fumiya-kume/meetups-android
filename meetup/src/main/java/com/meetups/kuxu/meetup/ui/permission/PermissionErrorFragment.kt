package com.meetups.kuxu.meetup.ui.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.meetups.kuxu.meetup.R
import com.meetups.kuxu.meetup.databinding.FragmentPermissionErrorBinding


class PermissionErrorFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  lateinit var binding: FragmentPermissionErrorBinding

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    // 複数の権限をリクエストする場合は条件分けを追加する
    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      refreshPermissionState()
    }
  }

  fun refreshPermissionState() {
    binding.locationPermissionMaterialButton.isEnabled = needPermissionForLocation()
    binding.locationPermissionMaterialButton.text = if (needPermissionForLocation()) {
      "位置情報を利用できるようにする"
    } else {
      "権限取得済み"
    }
    binding.backToPageMaterialButton.isEnabled = !needPermissionForLocation()

  }

  fun needPermissionForLocation(): Boolean {
    context?.let {
      return PermissionChecker.checkSelfPermission(
        it,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PermissionChecker.PERMISSION_GRANTED
    }
    return false
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding =
        FragmentPermissionErrorBinding.inflate(
          inflater,
          container,
          false
        )



    refreshPermissionState()

    binding.navigateAppSettingMaterialButton.setOnClickListener {
      navigateSettingActivity()
    }

    binding.locationPermissionMaterialButton.setOnClickListener {
      activity?.let {
        requestPermissions(
          arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
          0
        )
      }

    }

    binding.backToPageMaterialButton.setOnClickListener {
      Navigation.findNavController(it).navigate(R.id.action_permissionErrorFragment_to_nearMeetupOverviewFragment)
//      if (!findNavController().popBackStack()) {
//        val toast = Toast.makeText(context, "元の画面に戻るのに失敗しました。アプリを再起動してください", Toast.LENGTH_SHORT).show()
//      }
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
