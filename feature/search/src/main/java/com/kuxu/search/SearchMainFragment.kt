package com.kuxu.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.kuxu.navigation.SearchQueryEntity
import com.kuxu.search.databinding.FragmentSearchMainBinding
import org.koin.android.ext.android.inject

class SearchMainFragment : Fragment() {

    private val navController: NavController by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSearchMainBinding.inflate(
            inflater,
            container,
            false
        )

        binding.sarchEventMaterialButton.setOnClickListener {

            val keyword = binding.searchKeywordTextInputEditText.text.toString()

            val action =
                SearchMainFragmentDirections.actionRootSearchFragmentToSearchResultFragment(
                    SearchQueryEntity(keyword)
                )
            
            navController.navigate(action)
        }

        return binding.root
    }
}