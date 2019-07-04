package com.gsv28rus.sandbox.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(
        @Id val id: ObjectId,
        val firebaseUid: String,
        val name: String?,
        val email: String?,
        val password: String?
) {
    constructor(firebaseUid: String) : this(ObjectId.get(), firebaseUid, null, null, null)
    constructor(firebaseUid: String, name: String?, email: String?, password: String?) : this(ObjectId.get(), firebaseUid, name, email, password)
}