package com.kuxu.overview.ui

import com.kuxu.overview.ui.bindingmodel.MeetupOverviewBindingModel

internal interface OnOverviewMeetupEventItemClicked {
    fun onClick(meetupOverviewBindingModel: MeetupOverviewBindingModel)
}