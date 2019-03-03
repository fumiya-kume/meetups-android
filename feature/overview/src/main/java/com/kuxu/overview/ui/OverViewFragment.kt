package com.kuxu.overview.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.kuxu.overview.R
import com.kuxu.overview.databinding.FragmentOverViewBinding
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverViewFragment : Fragment() {

    private val overViewFragmentViewModel: OverViewFragmentViewModel by viewModel()
    private val navController: NavController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overViewFragmentViewModel.refreshMeetupList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            FragmentOverViewBinding.inflate(
                inflater,
                container,
                false
            )

        binding.mainMenuToolBar.inflateMenu(R.menu.main)

        binding.mainMenuToolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.setting_menu -> navigateSettingPage()
                else -> true
            }
        }

        binding.navigateSettingMaterialButton.setOnClickListener {
            navController.navigate(R.id.action_overViewFragment_to_rootSettingFragment)
        }

        val adapter = MeetupOverviewAdapter(requireContext())

        adapter.onOverviewMeetupEventItemClicked = object : OnOverviewMeetupEventItemClicked {
            override fun onClick(meetupOverviewBindingModel: MeetupOverviewBindingModel) {
                val chromeCustomTabsIntent = CustomTabsIntent.Builder().build()
                chromeCustomTabsIntent.launchUrl(
                    requireContext(),
                    Uri.parse(meetupOverviewBindingModel.eventUrl)
                )
            }
        }

        binding.overviewRecyclerView.adapter = adapter

        overViewFragmentViewModel.meetupOverviewLiveData.observeForever {
            adapter.submitList(
                it
            )
        }

        overViewFragmentViewModel.configuredOverviewSettingLiveData.observeForever {
            binding.isConfiguredsetting = it
        }

        overViewFragmentViewModel.networkErrorCallback = {
            Toast.makeText(
                requireContext(),
                "ネットワークエラーが発生しました",
                Toast.LENGTH_LONG
            ).show()
        }

        return binding.root
    }

    private fun navigateSettingPage(): Boolean {
        navController.navigate(R.id.action_overViewFragment_to_rootSettingFragment)
        return true
    }
}