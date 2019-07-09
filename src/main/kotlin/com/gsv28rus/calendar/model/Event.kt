package com.gsv28rus.calendar.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.gsv28rus.calendar.utils.ObjectIdSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "events")
data class Event(
        @Id
        @JsonSerialize(using = ObjectIdSerializer::class)
        val id: ObjectId?,

        @JsonSerialize(using = ObjectIdSerializer::class)
        var userId: ObjectId?,

        var title: String?,

        var description: String?,

        var location: String?,

        var startDate: Instant?,

        var endDate: Instant?,

        var who: String?,

        var repeat: Repeat?
)