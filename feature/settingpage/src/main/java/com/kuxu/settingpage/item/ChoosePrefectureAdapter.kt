package com.kuxu.settingpage.item

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kuxu.settingpage.bindingModel.PrefectureBindingModel

internal class ChoosePrefectureAdapter(
    private val context: Context
) : ListAdapter<PrefectureBindingModel, ChoosePrefectureViewHolder>(
    ITEM_CALLBACK
) {

    var onclickListener: ChoosePrefectureEventListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChoosePrefectureViewHolder = ChoosePrefectureViewHolder.create(
        LayoutInflater.from(context),
        parent
    )

    override fun onBindViewHolder(
        holder: ChoosePrefectureViewHolder,
        position: Int
    ) = holder.bind(
        getItem(position),
        onclickListener
    )

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<PrefectureBindingModel>() {
            override fun areItemsTheSame(
                oldItem: PrefectureBindingModel,
                newItem: PrefectureBindingModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PrefectureBindingModel,
                newItem: PrefectureBindingModel
            ): Boolean = oldItem == newItem
        }
    }
}