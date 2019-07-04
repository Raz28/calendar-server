package com.gsv28rus.sandbox.controllers

import com.gsv28rus.sandbox.model.User
import com.gsv28rus.sandbox.services.UserService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/users"])
class UserController(@Autowired private val userService: UserService) {

    @GetMapping("/all")
    fun getAll(): MutableList<User> {
        return userService.getAll()
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: ObjectId): Optional<User> {
        return userService.getUser(id)
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody user: User) {
        userService.updateUser(user)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteUser(@PathVariable id: ObjectId) {
        userService.deleteUser(id)
    }
}