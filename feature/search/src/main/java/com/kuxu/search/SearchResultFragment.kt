package com.kuxu.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kuxu.search.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            FragmentSearchResultBinding.inflate(
                inflater,
                container,
                false
            )

        arguments?.let {
            val arg = SearchResultFragmentArgs.fromBundle(it)
            binding.searchResultTextView.text = arg.searchQuery.keyword
        }

        return binding.root
    }
}