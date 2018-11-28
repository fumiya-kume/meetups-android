package com.meetups.kuxu.meetup.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.meetups.kuxu.meetup.R
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
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val canUseLocationPermission = PermissionChecker.checkSelfPermission(
      requireContext(),
      android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PermissionChecker.PERMISSION_GRANTED

    if (!canUseLocationPermission) {
      Navigation.findNavController(view).navigate(R.id.action_nearMeetupOverviewFragment_to_permissionErrorFragment)
    }
  }

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