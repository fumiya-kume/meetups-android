package com.kuxu.overview.domain

import com.kuxu.overview.entity.Prefecture

internal interface ChoosePrefectureRepository {
    suspend fun loadChoosePrefecture(): List<Prefecture>
    suspend fun hasConfigured(): Boolean
}