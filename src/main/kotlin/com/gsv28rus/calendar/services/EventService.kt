package com.gsv28rus.calendar.services

import com.gsv28rus.calendar.model.Event
import com.gsv28rus.calendar.model.Repeat
import com.gsv28rus.calendar.repo.EventRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Service
class EventService(@Autowired private val eventRepository: EventRepository) {

    fun getAllEventsByUserId(userId: ObjectId): List<Event> {
        val events: MutableList<Event> = mutableListOf()
        events.addAll(eventRepository.findByUserId(userId))
        events.addAll(getMockEvents(userId))
        return events
    }

    fun saveEvent(event: Event, userId: ObjectId) {
        event.userId = userId
        eventRepository.save(event)
    }

    fun deleteEvent(id: ObjectId) {
        eventRepository.deleteById(id)
    }

    private fun getMockEvents(userId: ObjectId?): MutableList<Event> {

        return mutableListOf(
                Event(ObjectId.get(), userId, "Test event #1", "Decription #1 Test text", "Location #1",
                        ZonedDateTime.now(), ZonedDateTime.now().plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #2", "Decription #2 Test text", "Location #2",
                        ZonedDateTime.now().plus(2, ChronoUnit.DAYS), ZonedDateTime.now().plus(2, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #3", "Decription #3 Test text", "Location #3",
                        ZonedDateTime.now().plus(4, ChronoUnit.DAYS), ZonedDateTime.now().plus(4, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #4", "Decription #4 Test text", "Location #4",
                        ZonedDateTime.now().plus(6, ChronoUnit.DAYS), ZonedDateTime.now().plus(6, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #5", "Decription #5 Test text", "Location #5",
                        ZonedDateTime.now().plus(8, ChronoUnit.DAYS), ZonedDateTime.now().plus(8, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT))
    }
}
