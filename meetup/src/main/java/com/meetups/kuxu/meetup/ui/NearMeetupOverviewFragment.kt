package com.meetups.kuxu.meetup.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
      val state = if (visivle) BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_HIDDEN
      searchNearMeetupBottonSheetBehavior.state =
          if (visivle) BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_HIDDEN
    }

    setVisibleSearchNearMeetupBottomSheet(false)

    binding.toolbar.inflateMenu(R.menu.event_overview_menu)
    binding.toolbar.setOnMenuItemClickListener {
      setVisibleSearchNearMeetupBottomSheet(true)
      true
    }


    val adapter = MeetupRowAdapter(requireContext())

    binding.nearRowRecyclerView.adapter = adapter

    viewModel.nearMeetupListLiveData.observeForever {
      adapter.submitList(it)
    }

    return binding.root
  }
}
