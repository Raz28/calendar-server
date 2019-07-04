package com.gsv28rus.sandbox.repo

import com.gsv28rus.sandbox.model.Event
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface EventRepository : MongoRepository<Event, ObjectId> {
    fun findByUserId(userId: ObjectId): List<Event>
}