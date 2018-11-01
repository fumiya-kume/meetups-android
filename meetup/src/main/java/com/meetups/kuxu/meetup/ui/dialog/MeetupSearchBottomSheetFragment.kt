package com.meetups.kuxu.meetup.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.meetups.kuxu.meetup.databinding.FragmentSearchMeetupDialogBinding
import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel

public class MeetupSearchBottomSheetFragment : BottomSheetDialogFragment() {

  internal var searchMeetupClickListener: OnSearchMeetupClickListener? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val binding =
      FragmentSearchMeetupDialogBinding.inflate(
        inflater,
        container,
        false
      )

    binding.bindingModel = MeetupSearchBindingModel(
      false,
      ""
    )

    binding.startSearchMaterialButton.setOnClickListener {
      searchMeetupClickListener?.let { listener ->
        binding.bindingModel?.let { bindingModel ->
          val keyword = binding.seasrchKeywordTextInputEditText.text.toString() ?: ""
          val nearSearch = binding.nearSearchCheckBox.isChecked

          listener.onClick(
            bindingModel.copy(
              nearSearch = nearSearch,
              keyword = keyword
            )
          )
        }
      }
    }
    return binding.root
  }
}