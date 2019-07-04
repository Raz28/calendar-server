package com.gsv28rus.sandbox.model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.gsv28rus.sandbox.utils.ObjectIdSerializer
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

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

        val period: PeriodEvent?,

        var who: String?,

        var repeat: Repeat?
)