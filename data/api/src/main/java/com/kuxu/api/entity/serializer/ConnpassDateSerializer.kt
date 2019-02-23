package com.kuxu.api.entity.serializer

import android.annotation.SuppressLint
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.withName
import java.text.SimpleDateFormat
import java.util.*

@Serializer(forClass = Date::class)
object ConnpassDateSerializer : KSerializer<Date> {

    @SuppressLint("SimpleDateFormat")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    override fun serialize(encoder: Encoder, obj: Date) = encoder.encodeString(dateFormat.format(obj))

    override fun deserialize(decoder: Decoder): Date = dateFormat.parse(decoder.decodeString())

    override val descriptor: SerialDescriptor
        get() = StringDescriptor.withName("ConnpassDataSerializer")
}