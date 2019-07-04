package com.gsv28rus.calendar.services

import com.google.firebase.auth.FirebaseAuth
import com.gsv28rus.calendar.model.User
import com.gsv28rus.calendar.repo.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun getUser(userId: ObjectId): Optional<User> {
        return userRepository.findById(userId)
    }

    fun getAll(): MutableList<User> {
        return userRepository.findAll()
    }

    fun updateUser(user: User) {
        userRepository.save(user)
    }

    fun deleteUser(id: ObjectId) {
        userRepository.deleteById(id)
    }
//
//    fun signIn(email: String, password: String) {
//        FirebaseAuth.getInstance().generateSignInWithEmailLinkAsync(email);
//    }
}