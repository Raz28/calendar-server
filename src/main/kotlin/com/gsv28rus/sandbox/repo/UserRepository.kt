package com.gsv28rus.sandbox.repo

import com.gsv28rus.sandbox.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByFirebaseUid(firebaseUid: String): User?
}