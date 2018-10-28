package com.meetups.kuxu.meetup.ui


import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.meetups.kuxu.meetup.R
import com.meetups.kuxu.meetup.databinding.FragmentNearMeetupOverviewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class NearMeetupOverviewFragment : Fragment() {

  private val viewModel: NearMeetupViewModel by viewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {


    val binding = FragmentNearMeetupOverviewBinding.inflate(
      inflater,
      container,
      false
    )

    val viewModel: NearMeetupViewModel by viewModel()

    fun setVisibleSearchNearMeetupBottomSheet(visivle: Boolean) {
      val searchNearMeetupBottonSheetBehavior = BottomSheetBehavior.from(binding.searchNearMeetupBottomSheet)
      if (visivle) BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_HIDDEN
      searchNearMeetupBottonSheetBehavior.state =
          if (visivle) BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_HIDDEN
    }

    setVisibleSearchNearMeetupBottomSheet(false)

    binding.toolbar.inflateMenu(R.menu.event_overview_menu)
    binding.toolbar.setOnMenuItemClickListener {
      if (PermissionChecker.checkSelfPermission(
          requireContext(),
          Manifest.permission.ACCESS_FINE_LOCATION
        ) == PermissionChecker.PERMISSION_GRANTED
      ) {
        viewModel.loadCurrentLocation(
          onError = {
            Snackbar.make(binding.searchNearMeetupBottomSheet, it, Snackbar.LENGTH_SHORT).show()
            return@loadCurrentLocation
          })
        setVisibleSearchNearMeetupBottomSheet(true)

      } else {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
            activity as Activity,
            Manifest.permission.ACCESS_FINE_LOCATION
          )
        ) {
          ActivityCompat.requestPermissions(
            activity as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            0
          )
        } else {
          Snackbar.make(
            binding.searchNearMeetupBottomSheet,
            "設定から権限を有効にしないと現在地からの検索が使えません...><",
            Snackbar.LENGTH_SHORT
          ).show()
        }
      }
      true
    }

    val adapter = MeetupRowAdapter(requireContext())

    binding.nearRowRecyclerView.adapter = adapter

    viewModel.meetupListLiveData.observeForever {
      adapter.submitList(it)
    }

    viewModel.currentLocationLiveData.observeForever {
      binding.lonResultTextView.text = it.lon.toString()
      binding.latResultTextView.text = it.lat.toString()
    }

    binding.searchNearMeetupMaterialButton.setOnClickListener {
      viewModel.searchWithLocation(
        onCurrentLocationMissing = { message ->
          Snackbar.make(
            binding.searchNearMeetupBottomSheet,
            message,
            Snackbar.LENGTH_SHORT
          ).show()
        }
      )
    }
    return binding.root
  }
}