package com.meetups.kuxu.meetup.ui.bindingModel

import com.meetups.kuxu.meetup.entity.LocationEntity

internal object CurrentLocationBindingModelConverter {
  internal fun convert(entity: LocationEntity): CurrentLocationBindingModel {
    return CurrentLocationBindingModel(entity.lat, entity.lon)
  }
}