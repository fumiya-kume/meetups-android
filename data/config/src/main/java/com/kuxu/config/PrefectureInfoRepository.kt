package com.kuxu.config

import com.kuxu.entity.Prefecture

class PrefectureInfoRepository {
    private val PrefectureList =
        listOf(
            Prefecture(0, "北海道"),
            Prefecture(1, "青森"),
            Prefecture(2, "秋田県"),
            Prefecture(3, "福島県"),
            Prefecture(4, "東京都")
        )

    fun loadPrefectureList(): List<Prefecture> = PrefectureList

    fun loadPrefectureFromId(id: Int) = PrefectureList.first { it.id == id }

}