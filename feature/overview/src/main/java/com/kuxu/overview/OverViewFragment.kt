package com.kuxu.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kuxu.overview.databinding.FragmentOverViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OverViewFragment : Fragment() {

    private val overViewFragmentViewModel: OverViewFragmentViewModel by viewModel()

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

        overViewFragmentViewModel.configuredOverviewSettingLiveData.observeForever {
            binding.isConfiguredsetting = it
        }

        return binding.root
    }
}