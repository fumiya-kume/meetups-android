package com.meetups.kuxu.meetup.ui.bindingModel

import com.meetups.kuxu.meetup.entity.CurrentLocationEntity

internal object CurrentLocationBindingModelConverter {
  internal fun convert(entity: CurrentLocationEntity): CurrentLocationBindingModel {
    return CurrentLocationBindingModel(entity.lat, entity.lon)
  }
}