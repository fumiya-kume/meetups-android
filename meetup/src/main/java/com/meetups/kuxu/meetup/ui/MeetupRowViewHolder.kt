package com.meetups.kuxu.meetup.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meetups.kuxu.meetup.databinding.ItemNearMeetupRowViewBinding
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel

internal class MeetupRowViewHolder(
  private val binding: ItemNearMeetupRowViewBinding
) : RecyclerView.ViewHolder(binding.root) {

  companion object {
    fun create(
      inflater: LayoutInflater,
      parent: ViewGroup
    ) =
      MeetupRowViewHolder(
        ItemNearMeetupRowViewBinding.inflate(
          inflater,
          parent,
          false
        )
      )
  }

  fun bindTo(
    bindingModel: MeetupRowBindingModel,
    eventSelectedListener: OnEventSelectedListener?
  ) {
    binding.bindingModel = bindingModel
    binding.root.setOnClickListener {
      eventSelectedListener?.onSelected(bindingModel)
    }
  }
}