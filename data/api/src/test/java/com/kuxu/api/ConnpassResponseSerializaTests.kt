package com.kuxu.api

import com.kuxu.api.entity.ConnpassAPIResponse
import com.kuxu.api.entity.serializer.ConnpassDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.junit.Assert.assertNotNull
import org.junit.Test
import java.util.*

@Serializable
data class DataConstruct(
    @Serializable(with = ConnpassDateSerializer::class)
    val date: Date
)


class ConnpassResponseSerializaTests {
    @Test
    fun serializaEmptyDate() {
        val sourceJson = """
    {
        "date": "2019-02-23T12:51:22+09:00"
    }
    """.trimIndent()
        val result = Json.parse(DataConstruct.serializer(), sourceJson)
        assertNotNull(result)
    }

    @Test
    fun Connpassから実際に落としてきたデータをパースすることができるかどうか() {
        val json = """
            {
               "results_returned":10,
               "events":[
                  {
                     "event_url":"https://retty.connpass.com/event/118544/",
                     "event_type":"participation",
                     "owner_nickname":"RettyTechCafe",
                     "series":{
                        "url":"https://retty.connpass.com/",
                        "id":2045,
                        "title":"Retty"
                     },
                     "updated_at":"2019-02-23T12:51:22+09:00",
                     "lat":"35.653293300000",
                     "started_at":"2019-03-16T11:00:00+09:00",
                     "hash_tag":"rettypy",
                     "title":"Python\u3082\u304f\u3082\u304f\u81ea\u7fd2\u5ba4 #18 @ Retty\u30aa\u30d5\u30a3\u30b9",
                     "event_id":118544,
                     "lon":"139.741211400000",
                     "waiting":1,
                     "limit":21,
                     "owner_id":59440,
                     "owner_display_name":"Retty",
                     "description":"<p>\u300c053\u3069\u3082\u306e\u305f\u3081\u306e\u30d7\u30ed\u30b0\u30e9\u30df\u30f3\u30b0\u9053\u5834\u3067\u3059\u3002</p>",
                     "address":"\u5c71\u68a8\u770c\u5317\u675c\u5e02\u9ad8\u6839\u753a\u6751\u5c71\u5317\u5272\uff13\uff12\uff18\uff18",
                     "catch":"\u30d7\u30ed\u30b0\u30e9\u30df\u30f3\u30b0\u3092\u697d\u3057\u3082\u3046!",
                     "accepted":1,
                     "ended_at":"2019-03-24T11:40:00+09:00",
                     "place":"\u5317\u675c\u5e02\u9ad8\u6839\u753a\u8fb2\u6751\u74b0\u5883\u6539\u5584\u30bb\u30f3\u30bf\u30fc"
                  }
               ],
               "results_start":1,
               "results_available":1000
            }
        """.trimIndent()

        val result = Json.parse(ConnpassAPIResponse.serializer(), json)
        assertNotNull(result)

    }
}