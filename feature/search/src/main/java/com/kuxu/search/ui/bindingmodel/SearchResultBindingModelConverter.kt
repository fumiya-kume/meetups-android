package com.kuxu.search.ui.bindingmodel

import com.kuxu.search.entity.MeetupResultEntity

internal fun MeetupResultEntity.convert(): SearchResultBindingModel =
    SearchResultBindingModel(this.title)

internal fun List<MeetupResultEntity>.convert(): List<SearchResultBindingModel> = this.map { it.convert() }