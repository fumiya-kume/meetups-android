package com.kuxu.overview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuxu.overview.databinding.ItemMeetupOverviewBinding
import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel

internal class MeetupOverviewViewHolder private constructor(
    private val binding: ItemMeetupOverviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup
        ) = MeetupOverviewViewHolder(
            ItemMeetupOverviewBinding.inflate(
                inflater,
                container,
                false
            )
        )
    }

    fun bind(
        bindingModel: MeetupOverviewBindingModel,
        onOverviewMeetupEventItemClicked: OnOverviewMeetupEventItemClicked?
    ) {
        binding.bindingModel = bindingModel
        binding.root.setOnClickListener {
            onOverviewMeetupEventItemClicked?.onClick(bindingModel)
        }
    }
}