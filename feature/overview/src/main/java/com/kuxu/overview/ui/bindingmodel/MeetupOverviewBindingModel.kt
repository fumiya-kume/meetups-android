package com.kuxu.overview.ui.bindingmodel

internal data class MeetupOverviewBindingModel(
    val id: Int,
    val title: String?,
    val holdingDate: String?,
    val holdingPlaceName: String?,
    val accept: Int,
    val limit: Int
)
