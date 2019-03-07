package com.kuxu.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kuxu.search.databinding.FragmentSearchMainBinding

class SearchMainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchMainBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }
}