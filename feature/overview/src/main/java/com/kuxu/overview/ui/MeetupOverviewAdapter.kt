package com.kuxu.overview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel

internal class MeetupOverviewAdapter(
    private val context: Context
) : ListAdapter<MeetupOverviewBindingModel, MeetupOverviewViewHolder>(
    ITEM_CALLBACK
) {
    companion object {
        private val ITEM_CALLBACK =
            object : DiffUtil.ItemCallback<MeetupOverviewBindingModel>() {
                override fun areItemsTheSame(
                    oldItem: MeetupOverviewBindingModel,
                    newItem: MeetupOverviewBindingModel
                ): Boolean = oldItem.id == newItem.id


                override fun areContentsTheSame(
                    oldItem: MeetupOverviewBindingModel,
                    newItem: MeetupOverviewBindingModel
                ): Boolean = oldItem == newItem
            }
    }

    var onOverviewMeetupEventItemClicked: OnOverviewMeetupEventItemClicked? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MeetupOverviewViewHolder =
        MeetupOverviewViewHolder.create(
            LayoutInflater.from(context),
            parent
        )

    override fun onBindViewHolder(
        holder: MeetupOverviewViewHolder,
        position: Int
    ) {
        holder.bind(
            getItem(position),
            onOverviewMeetupEventItemClicked
        )
    }


}

