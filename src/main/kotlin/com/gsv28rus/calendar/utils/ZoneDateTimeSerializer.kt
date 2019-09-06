package com.gsv28rus.calendar.utils

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME

class ZoneDateTimeSerializer : JsonSerializer<ZonedDateTime>() {
    override fun serialize(value: ZonedDateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.writeString(value?.format(ISO_ZONED_DATE_TIME.withZone( ZoneId.of("GMT"))))
    }
}

class ZoneDateTimeDeserializer : JsonDeserializer<ZonedDateTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): ZonedDateTime {
        return ZonedDateTime.parse(p?.text, ISO_ZONED_DATE_TIME)
    }
}
