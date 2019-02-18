package com.kuxu.settingpage.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuxu.settingpage.bindingModel.PrefectureBindingModel
import com.kuxu.settingpage.databinding.ChoosePrefectureItemBinding

internal class ChoosePrefectureViewHolder(
    private val binding: com.kuxu.settingpage.databinding.ChoosePrefectureItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            inflater: LayoutInflater,
            parent: ViewGroup
        ): ChoosePrefectureViewHolder {
            return ChoosePrefectureViewHolder(
                ChoosePrefectureItemBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    fun bind(
        bindingModel: PrefectureBindingModel,
        onClickListener: ChoosePrefectureEventListener?
    ) {
        binding.bindingModel = bindingModel
        onClickListener?.let {
            binding.root.setOnClickListener {
                onClickListener.onClick(bindingModel.id, !bindingModel.checked)
            }
        }
    }
}