package com.meetups.kuxu.meetup.ui.bindingModel

import com.meetups.kuxu.meetup.entity.NearMeetupEntity

internal object meetupRowBindingModelConverter {
  fun convert(entity: NearMeetupEntity) =
    meetupRowBindingModel(
      entity.id,
      entity.title,
      entity.meetupLink
    )

  fun convert(entityList: List<NearMeetupEntity>) = entityList.map { convert(it) }
}