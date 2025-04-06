// EventService.java
package com.example.eventmanagement.service;

import com.example.eventmanagement.model.Event;
import com.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Save Event (Create)
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get Event By ID (Read)
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Get All Events with Filtering, Pagination & Sorting
    public Page<Event> filterEvents(String name, String location, LocalDateTime startTime, LocalDateTime endTime, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return eventRepository.filterEvents(name, location, startTime, endTime, pageable);
    }

    // Update Event
    public Event updateEvent(Long id, Event event) {
        event.setId(id);
        return eventRepository.save(event);
    }

    // Delete Event
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
