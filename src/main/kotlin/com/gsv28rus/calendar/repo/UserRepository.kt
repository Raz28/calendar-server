package com.gsv28rus.calendar.repo

import com.gsv28rus.calendar.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByFirebaseUid(firebaseUid: String): User?
}