package com.meetups.kuxu.meetup.ui.dialog

import com.meetups.kuxu.meetup.ui.bindingModel.MeetupSearchBindingModel

internal interface OnSearchMeetupClickListener {
  fun onClick(bindingModel: MeetupSearchBindingModel)
}