package com.kuxu.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.kuxu.overview.databinding.FragmentOverViewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverViewFragment : Fragment() {

    private val overViewFragmentViewModel: OverViewFragmentViewModel by viewModel()
    private val navController: NavController by inject()

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

        overViewFragmentViewModel.configuredOverviewSettingLiveData.observeForever {
            binding.isConfiguredsetting = it
        }

        return binding.root
    }

    private fun navigateSettingPage(): Boolean {
        navController.navigate(R.id.action_overViewFragment_to_rootSettingFragment)
        return true
    }
}