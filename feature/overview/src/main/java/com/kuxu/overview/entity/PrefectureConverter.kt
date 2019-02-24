package com.kuxu.overview.entity


internal fun com.kuxu.entity.Prefecture.convert(): Prefecture = Prefecture(this.id, this.name)

internal fun List<com.kuxu.entity.Prefecture>.convert(): List<Prefecture> = this.map { it.convert() }