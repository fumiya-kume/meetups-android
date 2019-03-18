package com.kuxu.search.ui.bindingmodel

import java.text.SimpleDateFormat
import java.util.*

internal fun Date.toHoldingTimeString() = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.JAPAN).format(this)