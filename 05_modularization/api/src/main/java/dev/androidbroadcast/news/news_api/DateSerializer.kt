package dev.androidbroadcast.news.news_api

import android.annotation.SuppressLint
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

@Serializer(forClass = Date::class)
internal object DateSerializer: KSerializer<Date> {
    @SuppressLint("SimpleDateFormat")
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ ")

    override fun deserialize(decoder: Decoder): Date {
        return df.parse(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(df.format(value))
    }
}