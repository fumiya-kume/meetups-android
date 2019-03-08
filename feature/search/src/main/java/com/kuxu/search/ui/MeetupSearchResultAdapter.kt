package com.kuxu.search.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kuxu.search.ui.bindingmodel.SearchResultBindingModel

internal class MeetupSearchResultAdapter(
    private val context: Context
) : ListAdapter<SearchResultBindingModel, MeetupSearchResultViewHolder>(ITEM_CALLBACL) {

    companion object {
        val ITEM_CALLBACL =
            object : DiffUtil.ItemCallback<SearchResultBindingModel>() {
                override fun areItemsTheSame(
                    oldItem: SearchResultBindingModel,
                    newItem: SearchResultBindingModel
                ) = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: SearchResultBindingModel,
                    newItem: SearchResultBindingModel
                ) = oldItem == newItem

            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MeetupSearchResultViewHolder.create(
        LayoutInflater.from(context),
        parent
    )

    override fun onBindViewHolder(holder: MeetupSearchResultViewHolder, position: Int) {
        holder.bind(
            getItem(position)
        )
    }
}