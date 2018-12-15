package com.meetups.kuxu.meetup.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.meetups.kuxu.connpass_api.connpassApiModule
import com.meetups.kuxu.meetup.R
import com.meetups.kuxu.meetup.databinding.FragmentNearMeetupOverviewBinding
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel
import com.meetups.kuxu.meetup.ui.dialog.MeetupSearchBottomSheetFragment
import com.meetups.kuxu.meetup.ui.dialog.OnSearchMeetupClickListener
import kotlinx.android.synthetic.main.fragment_near_meetup_overview.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.standalone.StandAloneContext.loadKoinModules

/**
 * A simple [Fragment] subclass.
 *
 */
class NearMeetupOverviewFragment : Fragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val scope = getKoin().createScope("Meetup")
    loadKoinModules(connpassApiModule)
    scope.close()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    bottom_app_bar.inflateMenu(R.menu.setting_menu)

    bottom_app_bar.setOnMenuItemClickListener {
      Navigation.findNavController(view).navigate(R.id.action_global_setting_graph)
      true
    }

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
      fragmentManager?.let {
        meetupSearchBottomSheetFragment.show(it, meetupSearchBottomSheetFragment.tag)
      }


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