package com.kuxu.usersetting.schema

import com.kuxu.usersetting.entity.PrefectureList
import com.rejasupotaro.android.kvs.annotations.Key
import com.rejasupotaro.android.kvs.annotations.Table
import com.rejasupotaro.android.kvs.serializers.PrefsSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlinx.serialization.serializer

@Table(name = "user_setting")
class UserSettingsSchema {
    @Key(
        name = "target_prefecture_list",
        serializer = PrefectureListSealizer::class
    )
    val targetPrefectureList: String = ""
}

internal class PrefectureListSealizer : PrefsSerializer<PrefectureList, String> {
    override fun serialize(src: PrefectureList?): String =
        src?.let {
            return Json.stringify(
                Int.serializer().list,
                it.list
            )
        } ?: ""

    override fun deserialize(src: String?): PrefectureList =
        src?.let {
            PrefectureList(
                Json.parse(
                    Int.serializer().list,
                    src
                )
            )
        } ?: PrefectureList(emptyList())
}

