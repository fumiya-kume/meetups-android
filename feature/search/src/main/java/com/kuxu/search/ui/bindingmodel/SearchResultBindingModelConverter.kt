package com.kuxu.search.ui.bindingmodel

import com.kuxu.search.entity.MeetupResultEntity

internal fun MeetupResultEntity.convert(): SearchResultBindingModel =
    SearchResultBindingModel(
        this.id,
        this.title,
        this.holdingDate?.toHoldingTimeString() ?: "",
        this.holdingPlaceName,
        this.accept,
        this.limit,
        this.eventUrl
    )

internal fun List<MeetupResultEntity>.convert(): List<SearchResultBindingModel> = this.map { it.convert() }