package com.gsv28rus.calendar.services

import com.gsv28rus.calendar.model.Event
import com.gsv28rus.calendar.model.PeriodEvent
import com.gsv28rus.calendar.model.Repeat
import com.gsv28rus.calendar.repo.EventRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class EventService(@Autowired private val eventRepository: EventRepository) {

    fun getAll(): MutableList<Event> {
        return eventRepository.findAll()
    }

    fun getAllEvents(userId: ObjectId): List<Event> {
        val events: MutableList<Event> = mutableListOf()
        events.addAll(eventRepository.findByUserId(userId))
        events.addAll(getMockEvents(userId))
        return events
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

    fun getMockAllEvents(): MutableList<Event> {
        val mockNews = getMockEvents(ObjectId.get())
        return mockNews
    }

    public fun getMockEvents(userId: ObjectId?): MutableList<Event> {

        return  Arrays.asList(
                Event(ObjectId.get(), userId, "Test event #1", "Decription #1 Test text", "Location #1",
                        Instant.now(), Instant.now().plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #2", "Decription #2 Test text", "Location #2",
                        Instant.now().plus(2, ChronoUnit.DAYS), Instant.now().plus(2, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #3", "Decription #3 Test text", "Location #3",
                        Instant.now().plus(4, ChronoUnit.DAYS), Instant.now().plus(4, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #4", "Decription #4 Test text", "Location #4",
                        Instant.now().plus(6, ChronoUnit.DAYS), Instant.now().plus(6, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT),
                Event(ObjectId.get(), userId, "Test event #5", "Decription #5 Test text", "Location #5",
                        Instant.now().plus(8, ChronoUnit.DAYS), Instant.now().plus(8, ChronoUnit.DAYS).plus(2, ChronoUnit.HOURS),
                        "Test", Repeat.NOT_REPEAT))
    }
}