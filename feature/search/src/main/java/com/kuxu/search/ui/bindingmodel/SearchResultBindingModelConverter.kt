package com.kuxu.search.ui.bindingmodel

import com.kuxu.search.entity.EventEntity

internal fun EventEntity.convert(): SearchResultBindingModel =
    SearchResultBindingModel(
        this.id,
        this.title,
        this.holdingDate?.toHoldingTimeString() ?: "",
        this.holdingPlaceName,
        this.accept,
        this.limit,
        this.eventUrl
    )

internal fun List<EventEntity>.convert(): List<SearchResultBindingModel> = this.map { it.convert() }