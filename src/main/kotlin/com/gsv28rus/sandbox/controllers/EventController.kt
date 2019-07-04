package com.gsv28rus.sandbox.controllers

import com.gsv28rus.sandbox.model.Event
import com.gsv28rus.sandbox.services.EventService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/events"])
class EventController(@Autowired private val eventService: EventService) {

    @GetMapping("/all-in")
    fun getAll(): MutableList<Event> {
        return eventService.getAll()
    }

    @GetMapping("/all")
    fun getAll(@RequestParam userId: ObjectId): List<Event> {
        return eventService.getAllEvents(userId)
    }

    @GetMapping("/{id}")
    fun getEvent(@PathVariable id: ObjectId): Optional<Event> {
        return eventService.getEvent(id)
    }

    @PutMapping("/update")
    fun updateEvent(@RequestBody event: Event) {
        eventService.updateEvent(event)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteEvent(@PathVariable id: ObjectId) {
        eventService.deleteEvent(id)
    }
}