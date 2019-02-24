package com.kuxu.overview.ui.bindingmodel

import com.kuxu.overview.entity.MeetupEntity

internal fun MeetupEntity.convert(): MeetupOverviewBindingModel =
    MeetupOverviewBindingModel(
        this.id,
        this.title,
        this.holdingDate.toString(),
        this.holdingPlaceName,
        this.accept,
        this.limit
    )

internal fun List<MeetupEntity>.convert(): List<MeetupOverviewBindingModel> = this.map { it.convert() }