package com.meetups.kuxu.meetup.ui

import com.meetups.kuxu.meetup.ui.bindingModel.MeetupRowBindingModel

internal interface OnEventSelectedListener {
  fun onSelected(bindingModel: MeetupRowBindingModel)
}