package com.kuxu.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kuxu.search.databinding.FragmentSearchResultBinding
import com.kuxu.search.entity.EventSearchQuery
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment() {

    private val viewModel: SearchResultFragmentViewModel by viewModel()

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

        val adapter = MeetupSearchResultAdapter(requireContext())

        viewModel.searchResultLiveData.observeForever {
            adapter.submitList(it)
        }

        binding.meetupSearchResultRecyclerView.adapter = adapter

        arguments?.let {
            val arg = SearchResultFragmentArgs.fromBundle(it)

            val searchQuery = EventSearchQuery(arg.searchQuery.keyword)

            viewModel.search(searchQuery)
        }

        return binding.root
    }
}