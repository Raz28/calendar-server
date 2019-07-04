package com.gsv28rus.sandbox.services

import com.gsv28rus.sandbox.model.Event
import com.gsv28rus.sandbox.repo.EventRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventService(@Autowired private val eventRepository: EventRepository) {

    fun getAll(): MutableList<Event> {
        return eventRepository.findAll()
    }

    fun getAllEvents(userId: ObjectId): List<Event> {
        return eventRepository.findByUserId(userId)
    }

    fun getEvent(id: ObjectId): Optional<Event> {
        return eventRepository.findById(id)
    }

    fun updateEvent(event: Event) {
        eventRepository.save(event)
    }

    fun deleteEvent(id: ObjectId) {
        eventRepository.deleteById(id)
    }
}