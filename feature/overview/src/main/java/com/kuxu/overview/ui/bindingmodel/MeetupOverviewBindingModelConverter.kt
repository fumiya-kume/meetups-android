package com.kuxu.overview.ui.bindingmodel

import com.kuxu.overview.entity.MeetupEntity
import java.text.SimpleDateFormat
import java.util.*

internal fun MeetupEntity.convert(): MeetupOverviewBindingModel =
    MeetupOverviewBindingModel(
        this.id,
        this.title,
        this.holdingDate?.toHoldingTimeString() ?: "",
        this.holdingPlaceName,
        this.accept,
        this.limit
    )

internal fun Date.toHoldingTimeString() = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.JAPAN).format(this)

internal fun List<MeetupEntity>.convert(): List<MeetupOverviewBindingModel> = this.map { it.convert() }