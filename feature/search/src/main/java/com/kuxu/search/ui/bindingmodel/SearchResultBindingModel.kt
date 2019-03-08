package com.kuxu.search.ui.bindingmodel

internal data class SearchResultBindingModel(
    val id: Int,
    val title: String?,
    val holdingDate: String?,
    val holdingPlaceName: String?,
    val accept: Int,
    val limit: Int,
    val eventUrl: String
)