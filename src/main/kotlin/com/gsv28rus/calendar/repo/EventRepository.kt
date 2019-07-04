package com.gsv28rus.calendar.repo

import com.gsv28rus.calendar.model.Event
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface EventRepository : MongoRepository<Event, ObjectId> {
    fun findByUserId(userId: ObjectId): List<Event>
}