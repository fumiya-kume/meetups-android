package com.meetups.kuxu.meetup.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel

internal class MeetupRowAdapter(
  private val context: Context
) : ListAdapter<MeetupRowBindingModel, MeetupRowViewHolder>(
  ITEM_CALLBACK
) {
  companion object {

    private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<MeetupRowBindingModel>() {
      override fun areItemsTheSame(
        oldItem: MeetupRowBindingModel,
        newItem: MeetupRowBindingModel
      ): Boolean =
        oldItem.id == newItem.id

      override fun areContentsTheSame(
        oldItem: MeetupRowBindingModel,
        newItem: MeetupRowBindingModel
      ): Boolean =
        oldItem == newItem
    }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = MeetupRowViewHolder.create(
    LayoutInflater.from(context),
    parent
  )

  override fun onBindViewHolder(
    holder: MeetupRowViewHolder,
    position: Int
  ) {
    holder.bindTo(getItem(position))
  }
}