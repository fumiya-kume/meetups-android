package com.kuxu.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuxu.search.databinding.ItemMeetupSearchResultBinding
import com.kuxu.search.ui.bindingmodel.SearchResultBindingModel

internal class MeetupSearchResultViewHolder(
    private val binding: ItemMeetupSearchResultBinding
) : RecyclerView.ViewHolder(
    binding.root
) {

    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup
        ) = MeetupSearchResultViewHolder(
            ItemMeetupSearchResultBinding.inflate(
                inflater,
                container,
                false
            )
        )
    }

    fun bind(
        bindingModel: SearchResultBindingModel
    ) {
        binding.bindingModel = bindingModel
    }

}