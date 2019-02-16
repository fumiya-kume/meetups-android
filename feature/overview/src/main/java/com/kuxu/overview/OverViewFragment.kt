package com.kuxu.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kuxu.overview.databinding.FragmentOverViewBinding

class OverViewFragment : Fragment() {
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

        return binding.root
    }
}