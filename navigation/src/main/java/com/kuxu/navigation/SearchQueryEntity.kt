package com.kuxu.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchQueryEntity(
    val keyword: String
) : Parcelable