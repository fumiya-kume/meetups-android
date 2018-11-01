package com.meetups.kuxu.meetup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.meetups.kuxu.meetup.databinding.FragmentSearchMeetupDialogBinding

public class MeetupSearchBottomSheetFragment : BottomSheetDialogFragment() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

    val binding =
      FragmentSearchMeetupDialogBinding.inflate(
        inflater,
        container,
        false
      )

    return binding.root
  }
}