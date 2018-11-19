package com.meetups.kuxu.meetup.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.meetups.kuxu.meetup.databinding.FragmentNearMeetupOverviewBinding
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel
import com.meetups.kuxu.meetup.ui.dialog.MeetupSearchBottomSheetFragment
import com.meetups.kuxu.meetup.ui.dialog.OnSearchMeetupClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class NearMeetupOverviewFragment : Fragment() {

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

//    binding.toolbar.inflateMenu(R.menu.event_overview_menu)
//    binding.toolbar.setOnMenuItemClickListener {
//      if (PermissionChecker.checkSelfPermission(
//          requireContext(),
//          Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PermissionChecker.PERMISSION_GRANTED
//      ) {
//        when (it.itemId) {
//          R.id.near_search_item -> {
//            viewModel.loadCurrentLocation(
//              onError = {
//                return@loadCurrentLocation
//              })
//          }
//          R.id.seach_keyword_item -> {
//            val meetupSearchBottomSheetFragment = MeetupSearchBottomSheetFragment()
//            meetupSearchBottomSheetFragment.show(fragmentManager, meetupSearchBottomSheetFragment.tag)
//
//          }
//          else -> {
//            // 何もしない
//          }
//        }
//
//      } else {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(
//            activity as Activity,
//            Manifest.permission.ACCESS_FINE_LOCATION
//          )
//        ) {
//          ActivityCompat.requestPermissions(
//            activity as Activity,
//            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//            0
//          )
//        } else {
//
//        }
//      }
//      true
//    }

    val adapter = MeetupRowAdapter(requireContext())
    binding.nearRowRecyclerView.adapter = adapter
    viewModel.meetupListLiveData.observeForever {
      adapter.submitList(it)
    }
    binding.searchMeetupMaterialButton.setOnClickListener {
      val meetupSearchBottomSheetFragment = MeetupSearchBottomSheetFragment()
      meetupSearchBottomSheetFragment.show(fragmentManager, meetupSearchBottomSheetFragment.tag)

      meetupSearchBottomSheetFragment.searchMeetupClickListener = object :
        OnSearchMeetupClickListener {
        override fun onClick(bindingModel: MeetupSearchBindingModel) {

          viewModel.search(bindingModel)
          meetupSearchBottomSheetFragment.dismiss()
        }
      }
    }

    adapter.onEventSelectedListener = object : OnEventSelectedListener {
      override fun onSelected(bindingModel: MeetupRowBindingModel) {
        viewModel.showEventPage(bindingModel)
      }
    }

    return binding.root
  }
}