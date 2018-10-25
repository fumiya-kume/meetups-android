package com.meetups.kuxu.meetup.ui.bindingModel

import com.meetups.kuxu.meetup.entity.MeetupEntity

internal object meetupRowBindingModelConverter {
  fun convert(entity: MeetupEntity) =
    MeetupRowBindingModel(
      entity.id,
      entity.title,
      entity.meetupLink
    )

  fun convert(entityList: List<MeetupEntity>) = entityList.map { convert(it) }
}