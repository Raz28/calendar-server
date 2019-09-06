package com.gsv28rus.calendar.controllers

import com.gsv28rus.calendar.model.Event
import com.gsv28rus.calendar.security.FirebaseUserPrincipal
import com.gsv28rus.calendar.services.EventService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/events"])
class EventController(@Autowired private val eventService: EventService) {

    @GetMapping
    fun getUserEvents(authentication: Authentication): List<Event> {
        return eventService.getAllEventsByUserId(getUserId(authentication))
    }

    @PutMapping
    fun saveEvent(authentication: Authentication, @RequestBody event: Event) {
        eventService.saveEvent(event, getUserId(authentication))
    }

    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: ObjectId) {
        eventService.deleteEvent(id)
    }

    private fun getUserId(authentication: Authentication): ObjectId {
        return ObjectId(authentication.name)
    }
}
